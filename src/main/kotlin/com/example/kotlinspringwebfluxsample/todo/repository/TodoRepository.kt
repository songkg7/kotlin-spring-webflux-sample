package com.example.kotlinspringwebfluxsample.todo.repository

import com.example.kotlinspringwebfluxsample.todo.domain.Todo
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TodoRepository : ReactiveCrudRepository<Todo, Long> {
}