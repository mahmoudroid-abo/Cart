package com.mahmoudroid.cart.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoudroid.cart.R
import com.mahmoudroid.cart.adapter.CartAdapter
import com.mahmoudroid.cart.adapter.SavedItemsAdapter
import com.mahmoudroid.cart.databinding.ActivityCartBinding
import com.mahmoudroid.cart.viewmodel.CartViewModel

class CartActivity : AppCompatActivity(), CartAdapter.OnAddClickListener {

    private lateinit var binding: ActivityCartBinding

    private lateinit var cartViewModel: CartViewModel

    private lateinit var savedItemAdapter: SavedItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        savedItemAdapter = SavedItemsAdapter()

        initRecyclerView()

        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]


        cartViewModel.allCartItems.observe(this, androidx.lifecycle.Observer { cart ->
            cart.let {
                savedItemAdapter.setCartListItem(cart)
            }
        })

    }

    private fun initRecyclerView() {
        binding.savedItemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            val decoration = DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
            addItemDecoration(decoration)
            adapter = savedItemAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.clear_cart_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.deleteCartItems -> {
               cartViewModel.deleteAllItem()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}