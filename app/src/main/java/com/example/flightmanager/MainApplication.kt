package com.example.flightmanager

import android.app.Application
import android.content.Context
import com.example.flightmanager.di.module
import org.koin.android.ext.koin.androidContext

import org.koin.core.context.startKoin

class MainApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@MainApplication)


            modules(module)
        }
    }
}