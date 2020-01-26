package com.example.basemasterdetailsapplication.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.basemasterdetailsapplication.domain.DummyData

//ToDo 1 : Define your models
@Entity
data class DatabaseDummyData(
    @PrimaryKey
    val id: String,
    val price: Double,
    val type: String,
    val imgSrc: String
)

fun List<DatabaseDummyData>.asDomainModel(): List<DummyData> {
    return map {

        DummyData(
            id = it.id,
            type = it.type,
            price = it.price,
            imgSrc = it.imgSrc
        )
    }
}