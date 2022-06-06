package com.example.kotlinspringwebfluxsample.todo.domain

import java.time.LocalDateTime
import javax.persistence.Id

data class Todo(
    @Id
    var id: Long = 0,
    var content: String? = null,
    var done: Boolean = false,
    var createAt: LocalDateTime = LocalDateTime.now(),
    var modifiedAt: LocalDateTime = createAt
)