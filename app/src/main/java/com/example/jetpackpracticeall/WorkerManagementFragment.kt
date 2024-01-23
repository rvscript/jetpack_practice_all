package com.example.jetpackpracticeall

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.jetpackpracticeall.api.ApiServiceImpl
import com.example.jetpackpracticeall.models.ResponseMarsPhotos
import com.example.jetpackpracticeall.repository.NasaRepository
import com.example.jetpackpracticeall.repository.NasaRepositoryImpl
import com.example.jetpackpracticeall.utilities.Constants.Companion.KEY_URLS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class WorkerManagementFragment : Fragment() {
    lateinit var viewModel: JetPackViewModel
    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var apiService: ApiServiceImpl
    lateinit var repository: NasaRepository
    lateinit var mButtonWorkManager: Button
    lateinit var recyclerView: RecyclerView
    override fun onCreateView (
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
        recyclerView = view.findViewById(R.id.recyclerview_photos)

        viewModel.responseLiveData.observe(requireActivity()) {
            mButtonWorkManager.setOnClickListener { view ->
                setUpWorkManager(it)
            }
            setUpRV(it)
        }

        return view
    }

    private fun setUpRV(responseMarsPhotos: ResponseMarsPhotos) {
        if (responseMarsPhotos.photos.isNotEmpty()) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = NasaAdapter(responseMarsPhotos.photos)
        }
    }

    private fun setUpWorkManager(responseMarsPhotos: ResponseMarsPhotos) {
        // Jetpack WorkManager used to manage downloadFilesWorker()
        val photos = ArrayList<String>()
        if (responseMarsPhotos.photos.isNotEmpty()) {
            photos.addAll(responseMarsPhotos.photos.map { photo -> photo.img_src })
        }

        val outputFile = File(requireContext().applicationContext.filesDir,"urls.txt")
        outputFile.writeText("")
        outputFile.writeText(photos.toTypedArray().joinToString("\n"))
        val inputData = Data.Builder()
            .putString(KEY_URLS, Uri.fromFile(outputFile).toString())
            .build()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val downloadWorkRequest: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<DownLoadFilesWorker>()
                .setConstraints(constraints)
                .setInputData(inputData)
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