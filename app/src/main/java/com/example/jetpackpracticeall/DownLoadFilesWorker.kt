package com.example.jetpackpracticeall

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.os.Environment
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DownLoadFilesWorker(
    context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {
    @SuppressLint("Range")
    override suspend fun doWork(): Result = withContext(Dispatchers.IO){

        // demo key = bBLEcjpDC28Kvv8hU9qgDWUdSAl75KcbyKa5XmnL
        try {
            val fileUrl = "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG"
            val destinationDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            destinationDirectory.mkdirs()

            // create dl manager request with specified url
            val request = DownloadManager.Request(fileUrl.toUri())
                .setMimeType("image/jpeg")
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle("mars1.jpeg")
                .addRequestHeader("Authorization", "Bearer <token>")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "mars1.jpeg")
            // enqueue the download request and get the download ID
            val downloadManager = applicationContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadId = downloadManager.enqueue(request)

            // wait for the download to complete
            val query = DownloadManager.Query().setFilterById(downloadId)
            var downloading = true
            while(downloading) {
                val cursor: Cursor = downloadManager.query(query)
                if(cursor.moveToFirst()) {
                    val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                    if(status == DownloadManager.STATUS_SUCCESSFUL || status == DownloadManager.STATUS_FAILED) {
                        downloading = false
                    }
                }
                cursor.close()
            }
            // File has been downloaded successfully
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}