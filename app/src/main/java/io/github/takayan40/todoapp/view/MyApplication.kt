package io.github.takayan40.todoapp.view

import android.app.Application
import io.github.takayan40.todoapp.TodoStoreRepository

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        TodoStoreRepository.init(this)

    }
}