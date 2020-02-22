package io.github.takayan40.todoapp

import android.app.Application

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        TodoStoreRepository.init(this)

    }
}