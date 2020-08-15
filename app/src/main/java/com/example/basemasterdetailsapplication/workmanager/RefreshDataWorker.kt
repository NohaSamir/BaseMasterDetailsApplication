package com.example.basemasterdetailsapplication.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.basemasterdetailsapplication.data.source.repository.dataRepository

class RefreshDataWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        try {
            dataRepository.refreshList()
            return Result.success()
        } catch (e: Exception) {
            return Result.retry()
        }
    }
}