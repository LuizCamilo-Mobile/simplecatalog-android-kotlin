package br.com.simplecatalog_kotlin.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.simplecatalog_kotlin.databinding.ActivityMainBinding
import br.com.simplecatalog_kotlin.domain.state.UiState
import br.com.simplecatalog_kotlin.ui.adapter.ItemsAdapter
import br.com.simplecatalog_kotlin.ui.viewmodel.ItemsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemsAdapter
    private lateinit var viewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        adapter = ItemsAdapter()
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        viewModel = ViewModelProvider(this)[ItemsViewModel::class.java]

        viewModel.uiState.observe(this) { state ->
            render(state)
        }

        viewModel.loadItems()
    }

    private fun render(state: UiState) {
        when (state) {
            UiState.Loading -> {
                binding.progress.visibility = View.VISIBLE
                binding.recycler.visibility = View.GONE
                // se tiver empty/error view, esconder aqui também
            }

            UiState.Empty -> {
                binding.progress.visibility = View.GONE
                binding.recycler.visibility = View.GONE
                adapter.submitList(emptyList())
                // mostrar empty view, se existir
            }

            is UiState.Success -> {
                binding.progress.visibility = View.GONE
                binding.recycler.visibility = View.VISIBLE
                adapter.submitList(state.items)
            }

            is UiState.Error -> {
                binding.progress.visibility = View.GONE
                binding.recycler.visibility = View.GONE
                adapter.submitList(emptyList())
                // mostrar mensagem de erro, se existir
            }
        }
    }
}
