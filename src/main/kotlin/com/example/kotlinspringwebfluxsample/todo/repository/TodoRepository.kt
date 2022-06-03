package com.example.kotlinspringwebfluxsample.todo.repository

import com.example.kotlinspringwebfluxsample.todo.domain.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
}