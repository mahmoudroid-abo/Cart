package com.mahmoudroid.cart.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.mahmoudroid.cart.R
import com.mahmoudroid.cart.UploadCartWorker
import com.mahmoudroid.cart.adapter.CartAdapter
import com.mahmoudroid.cart.databinding.ActivityMainBinding
import com.mahmoudroid.cart.model.CartItem
import com.mahmoudroid.cart.viewmodel.CartViewModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), CartAdapter.OnAddClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var cartViewModel: CartViewModel

    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cartAdapter = CartAdapter(this)

        cartAdapter.setCartListItem(getData())
        initRecyclerView()

        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]


        initWorkManager()
    }

    private fun initRecyclerView() {
        binding.cartItemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
            addItemDecoration(decoration)
            adapter = cartAdapter
        }
    }

    override fun onAddClickListener(cartItem: CartItem) {
        cartViewModel.insertItem(cartItem)
        Toast.makeText(
            applicationContext,
            cartItem.itemName + " Added",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.cart_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.showCartItems -> {
                val intent = Intent(this, CartActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun getData(): List<CartItem> = listOf(
        CartItem(
            itemId = 1,
            itemName = "Tea"
        ),
        CartItem(
            itemId = 2,
            itemName = "Coffee"
        ),
        CartItem(
            itemId = 3,
            itemName = "Nescafe"
        ),
        CartItem(
            itemId = 4,
            itemName = "Sugar"
        ),
        CartItem(
            itemId = 5,
            itemName = "Water"
        ),
        CartItem(
            itemId = 6,
            itemName = "Green Tea"
        ),
        CartItem(
            itemId = 7,
            itemName = "Chocolate"
        ),
        CartItem(
            itemId = 8,
            itemName = "Biscuits"
        ),
        CartItem(
            itemId = 9,
            itemName = "Pasta"
        ),
        CartItem(
            itemId = 10,
            itemName = "Pepsi"
        ),
        CartItem(
            itemId = 11,
            itemName = "Coca Cola"
        ),
        CartItem(
            itemId = 12,
            itemName = "Oil"
        ),
        CartItem(
            itemId = 13,
            itemName = "Butter"
        ),
        CartItem(
            itemId = 14,
            itemName = "Cheese"
        ),
        CartItem(
            itemId = 15,
            itemName = "Salt"
        ),
        CartItem(
            itemId = 16,
            itemName = "Rice"
        ),
        CartItem(
            itemId = 17,
            itemName = "Meat"
        ),
        CartItem(
            itemId = 18,
            itemName = "Chicken"
        ),
        CartItem(
            itemId = 19,
            itemName = "Ketchup"
        ),
        CartItem(
            itemId = 20,
            itemName = "Milk"
        ),
    )

    fun initWorkManager() {
        val workManager = WorkManager.getInstance(this)
        val sendingLog = PeriodicWorkRequestBuilder<UploadCartWorker>(48, TimeUnit.HOURS)
            .addTag("TAG_SEND_LOG")
            .setInitialDelay(48, TimeUnit.HOURS)
            .build()
        workManager.enqueueUniquePeriodicWork(
            "upload-cart", ExistingPeriodicWorkPolicy.REPLACE, sendingLog
        )
        workManager.getWorkInfoByIdLiveData(sendingLog.id)
            .observe(this, Observer { workInfo ->
                when (workInfo.state) {
                    WorkInfo.State.ENQUEUED -> {

                    }
                    WorkInfo.State.RUNNING -> {

                    }
                    else -> {

                    }
                }
            })
    }

}
