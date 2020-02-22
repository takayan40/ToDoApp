package io.github.takayan40.todoapp

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.*

@Entity
data class Todo(
    @Id var id: Long = 0,
    var title: String = "",
    var detail: String = "",
    var priority: Int = 0,
    var deadline: Date? = null,
    var registed: Date? = null,
    var group: String = ""
)