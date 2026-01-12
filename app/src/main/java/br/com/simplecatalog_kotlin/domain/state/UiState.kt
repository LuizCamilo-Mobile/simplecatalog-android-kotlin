package br.com.simplecatalog_kotlin.domain.state

import br.com.simplecatalog_kotlin.domain.model.Item

sealed class UiState {
    data object Loading : UiState()
    data class Success(val items: List<Item>) : UiState()
    data object Empty : UiState()
    data class Error(val message: String) : UiState()
}
