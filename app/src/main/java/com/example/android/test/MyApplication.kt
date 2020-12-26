package com.example.android.test

import android.app.Application
import com.example.android.test.di.AppComponent
import com.example.android.test.di.DaggerAppComponent

class MyApplication: Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}