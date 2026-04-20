package repository

import model.Note
import java.util.UUID

interface NoteRepository {

    fun findAll(): List<Note>

    fun save(note: Note): Note

    fun delete(id: UUID): Unit

}