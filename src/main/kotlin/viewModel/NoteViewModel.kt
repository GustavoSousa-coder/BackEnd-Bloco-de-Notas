package viewModel

import model.Note
import model.enums.Day
import model.enums.Priority
import model.enums.Tags
import services.NoteService
import java.util.UUID

class NoteViewModel {

    private val service = NoteService()

    var notes: List<Note> = emptyList()
        private set

    var errorMessage: String? = null
        private set

    fun loadNotes() {
        errorMessage = null
        notes = service.findAll()
    }

    fun addNote(
        title: String,
        subTitle: String,
        content: String,
        day: Day,
        tag: Tags,
        priority: Priority
    ) {
        errorMessage = null

        try {
            val note = Note(
                title = title,
                subTitle = subTitle,
                content = content,
                day = day,
                tag = tag,
                priority = priority
            )
            service.save(note)
            loadNotes()
        } catch (e: IllegalArgumentException) {
            errorMessage = e.message
        }
    }

    fun removeNote(id: UUID) {
        try {
            service.delete(id)
            loadNotes()
        } catch (e: NoSuchElementException) {
            errorMessage = e.message
        }
    }
}