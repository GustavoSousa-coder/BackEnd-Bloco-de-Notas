package model

import model.enums.Day
import model.enums.Priority
import model.enums.Tags
import java.util.UUID

data class Note(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val subTitle: String,
    val content: String,
    val day: Day,
    val tag: Tags,
    val priority: Priority
)