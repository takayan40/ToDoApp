package io.github.takayan40.todoapp

import android.content.Context
import android.content.LocusId
import android.util.Log
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.kotlin.query

object TodoStoreRepository {

    lateinit var boxStore: BoxStore
    lateinit var todoBox: Box<Todo>

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }

    fun insert(todo: Todo) {
        todoBox = boxStore.boxFor(Todo::class.java)
        todoBox.put(todo)
    }

    fun update(todo: Todo) {
        todoBox = boxStore.boxFor(Todo::class.java)
        todoBox.put(todo)
    }

    fun delete(id: Long) {
        todoBox = boxStore.boxFor(Todo::class.java)
        todoBox.remove(id)
    }

    fun getAll(): ArrayList<Todo>? {
        todoBox = boxStore.boxFor(Todo::class.java)
        val todoList = todoBox.query().between(Todo_.priority, 0, 2).build().find()
        return todoList as ArrayList<Todo>
    }

    fun getComplete(): ArrayList<Todo>? {
        todoBox = boxStore.boxFor(Todo::class.java)
        val todoList = todoBox.query().equal(Todo_.priority, 3).build().find()
        return todoList as ArrayList<Todo>

    }
}