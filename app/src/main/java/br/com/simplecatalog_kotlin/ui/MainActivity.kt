package br.com.simplecatalog_kotlin.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.simplecatalog_kotlin.ui.viewmodel.ItemsViewModel
import br.com.simplecatalog_kotlin.databinding.ActivityMainBinding
import br.com.simplecatalog_kotlin.ui.adapter.ItemsAdapter


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: ItemsViewModel by viewModels()
    private val adapter = ItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        viewModel.loading.observe(this) { isLoading ->
            val show = isLoading == true
            binding.progress.visibility = if (show) View.VISIBLE else View.GONE
        }

        viewModel.items.observe(this) { list ->
            adapter.submitList(list)
        }

        viewModel.loadItems()
    }
}
