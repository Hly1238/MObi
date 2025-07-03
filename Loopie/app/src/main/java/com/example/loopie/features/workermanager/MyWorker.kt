package com.example.loopie.features.workermanager

import android.content.Context
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class MyWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Log.d("Success", "Work Manager Work")

        // Reschedule the next work with a 10-second delay
        val nextWorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniqueWork(
            "getAPI",
            ExistingWorkPolicy.REPLACE,
            nextWorkRequest
        )
        return Result.success()
    }
}