package com.example.jetpackpracticeall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.jetpackpracticeall.api.ApiService
import com.example.jetpackpracticeall.api.ApiServiceImpl
import com.example.jetpackpracticeall.models.Photo
import com.example.jetpackpracticeall.repository.NasaRepository
import com.example.jetpackpracticeall.repository.NasaRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkerManagementFragment : Fragment() {
    lateinit var viewModel: JetPackViewModel
    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var apiService: ApiServiceImpl
    lateinit var repository: NasaRepository
    lateinit var mButtonWorkManager: Button
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_worker_management, container, false)
        apiService = ApiServiceImpl()
        repository = NasaRepositoryImpl(apiService)
        viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[JetPackViewModel::class.java]
        mButtonWorkManager = view.findViewById(R.id.btn_workermanager)
        mButtonWorkManager.setOnClickListener {
            setUpWorkManager()
        }
        recyclerView = view.findViewById(R.id.recyclerview_photos)
        setUpRV()
        return view
    }

    private fun setUpRV() {
        viewModel.responseLiveData.observe(requireActivity()) {
            if (it.photos.isNotEmpty()) {
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = NasaAdapter(it.photos)
            }
        }
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