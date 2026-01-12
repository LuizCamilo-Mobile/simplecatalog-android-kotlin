package br.com.simplecatalog_kotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.simplecatalog_kotlin.domain.model.Item
import br.com.simplecatalog_kotlin.domain.state.UiState
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ItemsViewModel : ViewModel() {

    private val _uiState = MutableLiveData<UiState>(UiState.Empty)
    val uiState: LiveData<UiState> = _uiState

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    fun loadItems() {
        _uiState.value = UiState.Loading

        executor.execute {
            try {
                Thread.sleep(3000)
            } catch (ignored: InterruptedException) {
            }

            val result = hardcodedItems()

            val nextState = when {
                result.isEmpty() -> UiState.Empty
                else -> UiState.Success(result)
            }

            _uiState.postValue(nextState)
        }
    }

    private fun hardcodedItems(): List<Item> =
        listOf(
            Item(1, "Café", "500g • Torra média"),
            Item(2, "Leite", "Integral • 1L"),
            Item(3, "Arroz", "Tipo 1 • 5kg"),
            Item(4, "Feijão", "Carioca • 1kg"),
            Item(5, "Açúcar", "Cristal • 1kg")
        )

    override fun onCleared() {
        super.onCleared()
        executor.shutdownNow()
    }
}
