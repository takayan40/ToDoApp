package io.github.takayan40.todoapp.Presenter

import android.util.Log
import io.github.takayan40.todoapp.Todo
import io.github.takayan40.todoapp.TodoStoreRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddPresenter {
    fun add(title: String, detail: String, priority: Long, deadline: String, registed: Date) {
        val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN)
        val todo = Todo(
            title,
            detail,
            priority,
            sdf.parse(deadline),
            registed
        )

        GlobalScope.launch {
            TodoStoreRepository.insert(todo)
        }
    }
    fun update(title: String, detail: String, priority: Long, deadline: String, registed: Date){

    }
    fun delet(){

    }
}