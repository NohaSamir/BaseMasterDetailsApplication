package com.example.basemasterdetailsapplication.domain

import androidx.lifecycle.LiveData
import com.example.basemasterdetailsapplication.data.source.network.models.ResultWrapper

interface DataRepository {

    fun getList(): LiveData<List<DummyData>>

    suspend fun refreshList(): ResultWrapper<List<DummyData>>
}