package io.joseph.kospring

import io.joseph.kospring.domain.NoteHandler
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.router

@SpringBootApplication
class KoSpringApplication

fun main(args: Array<String>) {
    runApplication<KoSpringApplication>(*args)
}

@Component
class Routes(private val noteHandler: NoteHandler) {
    @Bean
    fun noteRouter() = router {
        GET("/api/notes", accept(MediaType.APPLICATION_JSON), noteHandler::getAll)
    }
}