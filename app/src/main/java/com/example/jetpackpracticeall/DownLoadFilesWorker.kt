package com.example.jetpackpracticeall

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.os.Environment
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.jetpackpracticeall.utilities.Constants.Companion.KEY_URLS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URI

class DownLoadFilesWorker(
    context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {
    @SuppressLint("Range")
    override suspend fun doWork(): Result = withContext(Dispatchers.IO){

        // demo key = bBLEcjpDC28Kvv8hU9qgDWUdSAl75KcbyKa5XmnL
        try {
//            val fileUrl = "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG"
            val urlsFilesUri = inputData.getString(KEY_URLS) ?: return@withContext Result.failure()
            val urls = File(URI.create(urlsFilesUri)).readLines().toTypedArray()
            val destinationDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            destinationDirectory.mkdirs()
            for (i in 0..1) {
                if (i < urls.size) {
                    downLoadManagerRequest(urls[i], i)
                }
            }
            // File has been downloaded successfully
            return@withContext Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
    @Throws(java.lang.Exception::class)
    private fun downLoadManagerRequest(fileUrl: String, index: Int) {
        // create dl manager request with specified url
        val request = DownloadManager.Request(fileUrl.toUri())
            .setMimeType("image/jpeg")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("mars$index.jpeg")
            .addRequestHeader("Authorization", "Bearer <token>")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "mars$index.jpeg")
        // enqueue the download request and get the download ID
        val downloadManager = applicationContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadId = downloadManager.enqueue(request)

        // wait for the download to complete
        val query = DownloadManager.Query().setFilterById(downloadId)
        var downloading = true
        while(downloading) {
            val cursor: Cursor = downloadManager.query(query)
            if(cursor.moveToFirst()) {
                val status = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))
                if(status == DownloadManager.STATUS_SUCCESSFUL || status == DownloadManager.STATUS_FAILED) {
                    downloading = false
                }
            }
            cursor.close()
        }
    }
}