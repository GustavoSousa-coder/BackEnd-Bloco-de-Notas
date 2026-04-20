package view

import model.enums.Day
import model.enums.Priority
import model.enums.Tags
import viewModel.NoteViewModel
import java.util.UUID

fun main() {
    val viewModel = NoteViewModel()

    while (true) {
        println("\n===== NOTAS =====")
        println("1. Listar notas")
        println("2. Adicionar nota")
        println("3. Remover nota")
        println("0. Sair")
        print("Escolha: ")

        when (readLine()?.trim()) {
            "1" -> listarNotas(viewModel)
            "2" -> adicionarNota(viewModel)
            "3" -> removerNota(viewModel)
            "0" -> {
                println("Encerrando...")
                return
            }
            else -> println("Opção inválida.")
        }
    }
}

fun listarNotas(viewModel: NoteViewModel) {
    viewModel.loadNotes()

    if (viewModel.notes.isEmpty()) {
        println("Nenhuma nota encontrada.")
        return
    }

    viewModel.notes.forEach { note ->
        println("\n[${note.id}]")
        println("  Título:    ${note.title}")
        println("  Subtítulo: ${note.subTitle}")
        println("  Conteúdo:  ${note.content}")
        println("  Dia:       ${note.day}")
        println("  Tag:       ${note.tag}")
        println("  Prioridade:${note.priority}")
    }
}

fun adicionarNota(viewModel: NoteViewModel) {
    print("Título: ")
    val title = readLine().orEmpty()

    print("Subtítulo: ")
    val subTitle = readLine().orEmpty()

    print("Conteúdo: ")
    val content = readLine().orEmpty()

    val day = escolherEnum("Dia", Day.entries)
    val tag = escolherEnum("Tag", Tags.entries)
    val priority = escolherEnum("Prioridade", Priority.entries)

    viewModel.addNote(title, subTitle, content, day, tag, priority)

    if (viewModel.errorMessage != null) {
        println("Erro: ${viewModel.errorMessage}")
    } else {
        println("Nota salva com sucesso!")
    }
}

fun removerNota(viewModel: NoteViewModel) {
    print("ID da nota para remover: ")
    val input = readLine()?.trim()

    try {
        val id = UUID.fromString(input)
        viewModel.removeNote(id)
        println("Nota removida.")
    } catch (e: IllegalArgumentException) {
        println("ID inválido.")
    }
}

fun <T : Enum<T>> escolherEnum(nome: String, opcoes: List<T>): T {
    println("$nome:")
    opcoes.forEachIndexed { index, value -> println("  $index. $value") }

    while (true) {
        print("Escolha (0-${opcoes.lastIndex}): ")
        val index = readLine()?.trim()?.toIntOrNull()
        if (index != null && index in opcoes.indices) {
            return opcoes[index]
        }
        println("Opção inválida, tente novamente.")
    }
}