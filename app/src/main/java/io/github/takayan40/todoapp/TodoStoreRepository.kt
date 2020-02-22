package io.github.takayan40.todoapp

import android.content.Context
import io.objectbox.BoxStore

object TodoStoreRepository {

    lateinit var boxStore:BoxStore

    fun init(context: Context){
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()

    }
}