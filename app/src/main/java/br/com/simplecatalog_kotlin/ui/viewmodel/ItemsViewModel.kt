package br.com.simplecatalog_kotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.simplecatalog_kotlin.domain.model.Item
import br.com.simplecatalog_kotlin.domain.state.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ItemsViewModel : ViewModel() {

    private val _uiState = MutableLiveData<UiState>(UiState.Empty)
    val uiState: LiveData<UiState> = _uiState

    fun loadItems() {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                delay(3000)  // Simula IO pesado sem bloquear a Main Thread

                val list = hardcodedItems()

                _uiState.value = if (list.isEmpty()) {
                    UiState.Empty
                } else {
                    UiState.Success(list)
                }

            } catch (e: Exception) {
                _uiState.value = UiState.Error("Falha ao carregar itens: ${e.message ?: "erro desconhecido"}")
            }
        }
    }

    private fun hardcodedItems() = listOf(
        Item(1, "Café", "500g • Torra média"),
        Item(2, "Leite", "Integral • 1L"),
        Item(3, "Arroz", "Tipo 1 • 5kg"),
        Item(4, "Feijão", "Carioca • 1kg"),
        Item(5, "Açúcar", "Cristal • 1kg")
    )
}
