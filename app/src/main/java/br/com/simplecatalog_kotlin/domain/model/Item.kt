package br.com.simplecatalog_kotlin.domain.model

/**
 * Item.kt é um modelo de domínio (domain model).
 *
 * Ele agrupa todos os dados do item no construtor e expõe apenas leitura
 * através de propriedades imutáveis (`val`).
 *
 * A imutabilidade garante segurança e previsibilidade no fluxo do app.
 */
data class Item(
    val id: Long,
    val title: String,
    val subtitle: String
)
