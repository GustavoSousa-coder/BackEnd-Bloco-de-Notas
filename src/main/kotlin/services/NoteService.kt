package services

import model.Note
import repository.NoteRepository
import repository.NoteRepositoryInMemory
import java.util.UUID

class NoteService {

    val repository: NoteRepository = NoteRepositoryInMemory()

    fun findAll(): List<Note> {
        if (repository.findAll().isEmpty()) {
            println("Note not found")
        }
        return repository.findAll()
    }

    fun save(note: Note): Note {
        if (note.title.isBlank())
            throw IllegalArgumentException("Título não pode ser vazio.")

        if (note.content.isBlank())
            throw IllegalArgumentException("Conteúdo não pode ser vazio.")

        val duplicator = repository.findAll().any { it.title == note.title }
        if (duplicator)
            throw IllegalArgumentException("Já existe uma nota com esse título.")
        return repository.save(note)
    }

    fun delete(id: UUID) {
        val exist = repository.findAll().any { it.id == id }
        if (!exist)
            throw NoSuchElementException("Nota com id '$id' não encontrada.")
        return repository.delete(id)
    }

}