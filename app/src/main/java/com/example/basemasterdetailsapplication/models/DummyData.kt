package com.example.basemasterdetailsapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

//ToDo 1 : Define your models
@Entity
data class DummyData(
    @PrimaryKey
    val id: String,
    val price: Double,
    val type: String,
    @Json(name = "img_src")
    val imgSrc: String
)