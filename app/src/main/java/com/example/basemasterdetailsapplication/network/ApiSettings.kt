package com.example.basemasterdetailsapplication.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//ToDo 2 : Change base url
private const val BASE_URL = "https://mars.udacity.com/"

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()
}


private val moshi: Moshi by lazy { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }

private val interceptor = HttpLoggingInterceptor()

private val client by lazy {

    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .build()
}