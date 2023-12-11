package io.joseph.kospring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.function.router

@SpringBootApplication
class KoSpringApplication

fun main(args: Array<String>) {
	runApplication<KoSpringApplication>(*args)
}

@Configuration
class Routes {
	@Bean
	fun mainRouter() = router {
		GET("/") { ok().body("Hello, world!") }
	}
}