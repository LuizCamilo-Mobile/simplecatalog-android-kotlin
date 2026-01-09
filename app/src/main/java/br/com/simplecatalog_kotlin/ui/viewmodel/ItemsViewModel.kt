package br.com.simplecatalog_kotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.simplecatalog_kotlin.domain.model.Item
import java.util.concurrent.Executors

class ItemsViewModel : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _items = MutableLiveData<List<Item>>(emptyList())
    val items: LiveData<List<Item>> = _items

    private val executor = Executors.newSingleThreadExecutor()

    fun loadItems() {
        _loading.value = true

        executor.execute {
            try { Thread.sleep(3000) } catch (_: InterruptedException) {}

            _items.postValue(hardcodedItems())
            _loading.postValue(false)
        }
    }

    private fun hardcodedItems() = listOf(
        Item(1, "Café", "500g • Torra média"),
        Item(2, "Leite", "Integral • 1L"),
        Item(3, "Arroz", "Tipo 1 • 5kg"),
        Item(4, "Feijão", "Carioca • 1kg"),
        Item(5, "Açúcar", "Cristal • 1kg")
    )

    override fun onCleared() {
        executor.shutdownNow()
    }
}
