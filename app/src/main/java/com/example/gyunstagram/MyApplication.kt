package com.example.gyunstagram

import android.app.Application
import com.example.gyunstagram.di.myDiModule
import org.koin.core.context.startKoin

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            this@MyApplication
            myDiModule
        }
    }
}