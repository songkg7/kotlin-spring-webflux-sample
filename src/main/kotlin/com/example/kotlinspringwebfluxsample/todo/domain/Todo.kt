package com.example.kotlinspringwebfluxsample.todo.domain

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import javax.persistence.Id

@Table("todos")
data class Todo(
    @Id
    var id: Long = 0,
    var content: String? = null,
    var done: Boolean = false,
    var createAt: LocalDateTime = LocalDateTime.now(),
    var modifiedAt: LocalDateTime = createAt,
)