package io.github.takayan40.todoapp

import android.content.Context
import android.util.Log
import io.objectbox.Box
import io.objectbox.BoxStore

object TodoStoreRepository {

    lateinit var boxStore: BoxStore
    lateinit var todoBox: Box<Todo>

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }

    fun insert(todoModel: Todo) {
        todoBox = boxStore.boxFor(Todo::class.java)
        todoBox.put(todoModel)
    }

    fun get(): ArrayList<Todo>? {
        todoBox = boxStore.boxFor(Todo::class.java)
        val todoList = todoBox.all as ArrayList<Todo>
        Log.d("tag", todoList.size.toString())

        return todoList
    }
}