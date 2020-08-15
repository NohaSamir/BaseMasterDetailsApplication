package com.example.basemasterdetailsapplication.data.source.network.models

//Construct this model based on backend error model
data class ErrorResponse(
    val code: Int,
    val message: String
)