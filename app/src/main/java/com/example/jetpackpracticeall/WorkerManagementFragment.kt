package com.example.jetpackpracticeall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkerManagementFragment : Fragment() {
    lateinit var mButtonWorkManager: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_worker_management, container, false)
        mButtonWorkManager = view.findViewById(R.id.btn_workermanager)
        mButtonWorkManager.setOnClickListener(View.OnClickListener {
            setUpWorkManager()
        })
        return view
    }

    private fun setUpWorkManager() {
        // Jetpack WorkManager used to manage downloadFilesWorkder()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val downloadWorkRequest: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<DownLoadFilesWorker>()
                .setConstraints(constraints)
                .build()
        CoroutineScope(Dispatchers.IO).launch {

            // enqueue the work request
            WorkManager.getInstance(this@WorkerManagementFragment.requireActivity())
                .enqueue(downloadWorkRequest)

        }

        // Observe the work request status
        WorkManager.getInstance(this@WorkerManagementFragment.requireActivity())
            .getWorkInfoByIdLiveData(downloadWorkRequest.id)
            .observe(this@WorkerManagementFragment.requireActivity()) { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    // work succeeded do additional action here
                    Toast.makeText(
                        this@WorkerManagementFragment.requireContext(),
                        "FILE Download SUCCEEDED",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}