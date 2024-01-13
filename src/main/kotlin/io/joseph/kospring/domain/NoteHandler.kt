package io.joseph.kospring.domain

import org.springframework.stereotype.Service
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

@Service
class NoteHandler(private val noteRepository: NoteRepository) {
    fun getAll(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.bind(NoteRequest::class.java)
        return ServerResponse.ok().body(
            noteRepository.findAll().map { note ->
                NoteResponse(
                    id = note.id,
                    title = note.title,
                    content = note.content
                )
            }
        )
    }
}

data class NoteResponse(
    val id: Long,
    val title: String,
    val content: String
)

data class NoteRequest(
    val page: Int = 0,
    val pageSize: Int = 10
)