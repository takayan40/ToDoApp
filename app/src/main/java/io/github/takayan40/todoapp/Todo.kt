package io.github.takayan40.todoapp

import java.util.*

class Todo(
    var title: String = "",
    var detail: String = "",
    var priority: Long = 0,
    var deadline: Date? = null,
    var registed: Date? = null,
    var group: String = ""
){
    constructor(todoModel: TodoModel) : this() {
        title = todoModel.title
        detail = todoModel.detail
        priority = todoModel.priority
        deadline = todoModel.deadline
        registed = todoModel.registed
        group = todoModel.group
    }
}