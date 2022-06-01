package com.backbase.assignment.ui

import android.app.Application
import com.backbase.assignment.ui.di.KoinCoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieBoxApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        val moduleList = listOf(KoinCoreModule().viewModelModule,KoinCoreModule().networkModule)
        startKoin{
            androidContext(this@MovieBoxApplication)
            modules(moduleList)
        }
    }
}