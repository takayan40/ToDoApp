package io.github.takayan40.todoapp

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.*

@Entity
class TodoModel() {
    @Id
    var id: Long = 0
    var title: String = ""
    var detail: String = ""
    var priority: Long = 0
    var deadline: Date? = null
    var registed: Date? = null
    var group: String = ""

    constructor(todo: Todo) : this() {
        this.title = todo.title
        this.detail = todo.detail
        this.priority = todo.priority
        this.deadline = todo.deadline
        this.registed = todo.registed
        this.group = todo.group
    }

}