package br.com.simplecatalog_kotlin.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.simplecatalog_kotlin.ui.viewmodel.ItemsViewModel
import br.com.simplecatalog_kotlin.databinding.ActivityMainBinding
import br.com.simplecatalog_kotlin.domain.model.Item
import br.com.simplecatalog_kotlin.domain.state.UiState
import br.com.simplecatalog_kotlin.ui.adapter.ItemsAdapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: ItemsViewModel by viewModels()
    private val adapter = ItemsAdapter(::onItemClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        viewModel.uiState.observe(this) { state ->
            when (state) {
                is UiState.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }

                is UiState.Success -> {
                    binding.progress.visibility = View.GONE
                    adapter.submitList(state.items)
                }

                is UiState.Empty -> {
                    binding.progress.visibility = View.GONE
                    adapter.submitList(emptyList())
                    // opcional: mostrar um "empty state" no layout
                }

                is UiState.Error -> {
                    binding.progress.visibility = View.GONE
                    adapter.submitList(emptyList())
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.loadItems()
    }

    private fun onItemClicked(item: Item) {
        Toast.makeText(this, "Clicou: ${item.title}", Toast.LENGTH_SHORT).show()
        // ou qualquer outra ação futura: abrir detalhe, navegar, log, etc.
    }

}
