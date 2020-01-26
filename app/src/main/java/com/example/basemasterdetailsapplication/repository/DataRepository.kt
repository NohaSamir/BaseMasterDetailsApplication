package com.example.basemasterdetailsapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.basemasterdetailsapplication.Injection
import com.example.basemasterdetailsapplication.database.AppDatabase
import com.example.basemasterdetailsapplication.database.models.asDomainModel
import com.example.basemasterdetailsapplication.domain.DummyData
import com.example.basemasterdetailsapplication.network.AppApiServices
import com.example.basemasterdetailsapplication.network.apiServices
import com.example.basemasterdetailsapplication.network.models.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val dataRepository by lazy {
    DataRepository(AppDatabase.getInstance(Injection.application), apiServices)
}

class DataRepository(
    private val database: AppDatabase,
    private val webServices: AppApiServices
) {

    val list: LiveData<List<DummyData>> = Transformations.map(database.dataDao.getAllData())
    {
        it.asDomainModel()
    }


    suspend fun refreshList() {
        withContext(Dispatchers.IO)
        {
            val list = webServices.getProperties().await()
            database.dataDao.insert(list = *list.asDatabaseModel())
        }
    }
}