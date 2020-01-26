package com.example.basemasterdetailsapplication.network

import com.example.basemasterdetailsapplication.network.models.NetworkDummyData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

val apiServices: AppApiServices by lazy {
    retrofit.create(AppApiServices::class.java)
}

interface AppApiServices {

    //ToDo 3 : Add App Web Services
    @GET("realestate")
    fun getProperties(): Deferred<List<NetworkDummyData>>
}