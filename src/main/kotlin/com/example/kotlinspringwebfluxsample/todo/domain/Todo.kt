package com.example.kotlinspringwebfluxsample.todo.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Lob

@Entity
class Todo {

    @Id
    @GeneratedValue
    var id: Long = 0;

    @Lob
    var content: String? = null

    var done: Boolean = false

    var createAt: LocalDateTime = LocalDateTime.now()

    var modifiedAt: LocalDateTime = createAt

}