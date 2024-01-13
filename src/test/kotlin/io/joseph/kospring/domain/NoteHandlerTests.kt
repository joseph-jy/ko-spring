package io.joseph.kospring.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.apache.http.HttpStatus
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.web.servlet.function.ServerRequest
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class NoteHandlerTests : BehaviorSpec({
    given("a note handler") {
        val noteRepository = mockk<NoteRepository>()
        val noteHandler = NoteHandler(noteRepository = noteRepository)
        every { noteRepository.findAll() } returns listOf(
            Note(
                id = 1,
                title = "TEST",
                content = "content",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )
        `when`("getAll is called") {
            val request = mockk<ServerRequest>()
            val response = noteHandler.getAll(request)
            then("it should return all notes") {
                verify { noteRepository.findAll() }
                response.statusCode().value() shouldBe HttpStatus.SC_OK
            }
        }
    }
})