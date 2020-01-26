package com.example.basemasterdetailsapplication

import android.app.Application
import android.os.Build
import androidx.work.*
import com.example.basemasterdetailsapplication.workmanager.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MyApp : Application() {

    val coroutineScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        Injection.integrateWith(this)
        setupRecurringWork()
    }

    private fun setupRecurringWork() {

        coroutineScope.launch {

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setRequiresDeviceIdle(true)
                    }
                }.build()

            val repeatingRequest =
                PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
                    .setConstraints(constraints).build()

            WorkManager.getInstance().enqueueUniquePeriodicWork(
                RefreshDataWorker.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest
            )
        }
    }
}