package com.example.kotlinspringwebfluxsample.todo.handler

import com.example.kotlinspringwebfluxsample.todo.domain.Todo
import com.example.kotlinspringwebfluxsample.todo.repository.TodoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.EntityResponse.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI
import java.time.LocalDateTime
import java.util.*

@Component
class TodoHandler(private val repository: TodoRepository) {
    fun getAll(request: ServerRequest) =
        ok().contentType(MediaType.APPLICATION_JSON).body(repository.findAll(), Todo::class.java)

    fun getById(request: ServerRequest) =
        repository.findById(request.pathVariable("id").toLong())
            .flatMap { ok().bodyValue(fromObject(it)) }

    fun save(request: ServerRequest) =
        repository.saveAll(request.bodyToMono(Todo::class.java))
            .flatMap { created(URI.create("/todo/${it.id}")).build() }
            .next()

    fun done(request: ServerRequest) =
        repository.findById(request.pathVariable("id").toLong())
            .filter(Objects::nonNull)
            .flatMap { todo ->
                todo.done = true
                todo.modifiedAt = LocalDateTime.now()
                repository.save(todo)
            }
            .flatMap {
                it.let { ok().build() }
            }
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun delete(request: ServerRequest) =
        repository.findById(request.pathVariable("id").toLong())
            .filter(Objects::nonNull)
            .flatMap { todo -> ok().build(repository.deleteById(todo.id)) }
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())
}