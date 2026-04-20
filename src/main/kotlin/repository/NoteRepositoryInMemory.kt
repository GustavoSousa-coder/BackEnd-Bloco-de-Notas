package repository

import model.Note
import java.util.*
import java.util.concurrent.ConcurrentHashMap


class NoteRepositoryInMemory : NoteRepository {

    private val map: MutableMap<UUID?, Note?> = ConcurrentHashMap()

    override fun findAll(): List<Note> {
        return ArrayList(map.values.filterNotNull())
    }

    override fun save(note: Note): Note {
        map[note.id] = note
        return note
    }

    override fun delete(id: UUID) {
        map.remove(id)
    }

}