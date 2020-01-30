package com.example.basemasterdetailsapplication.data.source.network.models

import com.example.basemasterdetailsapplication.data.source.database.models.DatabaseDummyData
import com.example.basemasterdetailsapplication.domain.DummyData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//ToDo 1 : Define your models
@JsonClass(generateAdapter = true)
data class NetworkDummyData(
    val id: String,
    val price: Double,
    val type: String,
    @Json(name = "img_src")
    val imgSrc: String
)

fun List<NetworkDummyData>.asDomainModel(): List<DummyData> {
    return map {

        DummyData(
            id = it.id,
            type = it.type,
            price = it.price,
            imgSrc = it.imgSrc
        )
    }
}

fun List<NetworkDummyData>.asDatabaseModel(): Array<DatabaseDummyData> {
    return map {

        DatabaseDummyData(
            id = it.id,
            type = it.type,
            price = it.price,
            imgSrc = it.imgSrc
        )
    }.toTypedArray()
}