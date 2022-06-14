package com.example.testmatajar.views.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.testmatajar.R
import com.example.testmatajar.databinding.ActivityHomeBinding
import com.example.testmatajar.provider.OrderItemProvider
import com.example.testmatajar.views.ItemDetail.ItemDetailActivity
import timber.log.Timber

class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()

        setupRecyclerView()

        setupObserver()
    }


    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setupRecyclerView() {
        val item = object : ItemInterface {


            override fun cartOptions(
                type: String,
                position: Int,
                orderItemProvider: OrderItemProvider
            ) {
                when (type) {
                    "view" -> {
                        val intent =
                            Intent(this@HomeActivity, ItemDetailActivity::class.java)
                        intent.putExtra("from", "cart")
                        intent.putExtra("productId", orderItemProvider.pkProductId)
                        startActivity(intent)
                    }

                }
            }

        }
        adapter = HomeAdapter(item)
        binding.itemRecycler.adapter = adapter
    }

    private fun setupObserver() {

        viewModel.getProducts().observe(this) {
            Timber.d("products --> $it")
            adapter.submitList(it)
        }
        viewModel.getError().observe(this) {
            Timber.d("error --> $it")
        }

    }
}