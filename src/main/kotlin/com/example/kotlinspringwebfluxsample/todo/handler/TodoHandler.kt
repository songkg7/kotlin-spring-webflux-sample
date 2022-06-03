package com.example.kotlinspringwebfluxsample.todo.handler

import com.example.kotlinspringwebfluxsample.todo.domain.Todo
import com.example.kotlinspringwebfluxsample.todo.repository.TodoRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class TodoHandler(private val repository: TodoRepository) {
    fun getAll(request: ServerRequest): Mono<ServerResponse> {
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<List<Todo>>(Mono.just(repository.findAll()))
            .switchIfEmpty(notFound().build())
    }


}