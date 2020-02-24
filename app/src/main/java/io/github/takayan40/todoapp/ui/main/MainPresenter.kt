package io.github.takayan40.todoapp.ui.main

import io.github.takayan40.todoapp.Todo
import io.github.takayan40.todoapp.TodoStoreRepository
import io.github.takayan40.todoapp.ViewHolder

class MainPresenter(val view: ViewHolder.HomeView){

    fun dataUpdate(){
        val todoList = TodoStoreRepository.get()
        view.updateAdapter(todoList as ArrayList<Todo>)
    }
}