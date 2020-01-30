package com.example.basemasterdetailsapplication.domain

import androidx.lifecycle.LiveData

interface DataRepository {

    fun getList(): LiveData<List<DummyData>>

    suspend fun refreshList()
}