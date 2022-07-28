package com.mahmoudroid.cart.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mahmoudroid.cart.model.CartItem
import com.mahmoudroid.cart.repository.CartRepository

class CartViewModel(application: Application) : AndroidViewModel(application) {

    val allCartItems: LiveData<List<CartItem>>
    private val repository = CartRepository(application)

    init {
        allCartItems = repository.allCartItems
    }

    fun insertItem(cartItem: CartItem) {
        repository.insertItem(cartItem)
    }

    fun updateItem(cartItem: CartItem) {
        repository.updateItem(cartItem)
    }

    fun deleteItem(cartItem: CartItem) {
        repository.deleteItem(cartItem)
    }

    fun deleteAllItem() {
        repository.deleteAllItems()
    }
}