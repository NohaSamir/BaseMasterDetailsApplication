package com.example.basemasterdetailsapplication.data.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.basemasterdetailsapplication.Injection
import com.example.basemasterdetailsapplication.data.source.database.AppDatabase
import com.example.basemasterdetailsapplication.data.source.database.models.asDomainModel
import com.example.basemasterdetailsapplication.data.source.network.AppApiServices
import com.example.basemasterdetailsapplication.data.source.network.apiServices
import com.example.basemasterdetailsapplication.data.source.network.models.asDatabaseModel
import com.example.basemasterdetailsapplication.domain.DataRepository
import com.example.basemasterdetailsapplication.domain.DataStatus
import com.example.basemasterdetailsapplication.domain.DummyData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val dataRepository by lazy {
    DataRepositoryImpl(AppDatabase.getInstance(Injection.application), apiServices)
}

class DataRepositoryImpl(
    private val database: AppDatabase,
    private val webServices: AppApiServices
) : DataRepository {


    override suspend fun refreshList(dataStatus: MutableLiveData<DataStatus>?) {
        withContext(Dispatchers.IO)
        {
            dataStatus?.postValue(DataStatus.LOADING)
            try {

                val list = webServices.getProperties().await()
                database.dataDao.insert(list = *list.asDatabaseModel())

                dataStatus?.postValue(DataStatus.SUCCESS)

            } catch (e: Exception) {
                dataStatus?.postValue(DataStatus.ERROR)
            }

        }
    }

    override fun getList(): LiveData<List<DummyData>> {
        return Transformations.map(database.dataDao.getAllData())
        {
            it.asDomainModel()
        }
    }
}