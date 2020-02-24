package io.github.takayan40.todoapp

import android.content.Context
import android.util.Log
import io.objectbox.Box
import io.objectbox.BoxStore

object TodoStoreRepository {

    lateinit var boxStore: BoxStore
    lateinit var todoBox: Box<TodoModel>

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }

    fun insert(todoModel: TodoModel) {
        todoBox = boxStore.boxFor(TodoModel::class.java)
        todoBox.put(todoModel)
    }

    fun get():ArrayList<Todo>?{
        todoBox = boxStore.boxFor(TodoModel::class.java)
        val todoModelList = todoBox.all
        Log.d("tag", todoModelList.size.toString())

        val todoList=ArrayList<Todo>()

        for (todoModel in todoModelList) {
            val todo = Todo(todoModel)
            todoList.add(todo)
        }

        return todoList
    }
}