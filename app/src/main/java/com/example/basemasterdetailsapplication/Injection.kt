package com.example.basemasterdetailsapplication

import android.app.Application

object Injection {

    internal lateinit var application: Application private set

    fun integrateWith(application: Application) {
        this.application = application
    }
}