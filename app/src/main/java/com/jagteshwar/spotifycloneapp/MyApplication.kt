package com.jagteshwar.spotifycloneapp

import android.app.Application
import com.jagteshwar.spotifycloneapp.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            modules(defaultModule, NetworkModule().module)
        }
    }
}