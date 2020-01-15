package com.example.basemasterdetailsapplication.models

import com.squareup.moshi.Json

//ToDo 1 : Define your models
data class DummyData(
    val id: String,
    val price: Double,
    val type: String,
    @Json(name = "img_src")
    val imgSrc: String
)