package com.example.basemasterdetailsapplication.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//ToDo 1 : Define your models
@Parcelize
data class DummyData(
    val id: String,
    val price: Double,
    val type: String,
    val imgSrc: String
) : Parcelable