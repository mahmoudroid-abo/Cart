package com.mahmoudroid.cart.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.mahmoudroid.cart.database.CartDao
import com.mahmoudroid.cart.database.CartDataBase
import com.mahmoudroid.cart.model.CartItem

class CartRepository(application: Application) {

    private val cartDao: CartDao
    val allCartItems: LiveData<List<CartItem>>

    init {
        val cartDb = CartDataBase.getDatabase(application)

        cartDao = cartDb!!.cartDao()
        allCartItems = cartDao.getAllItems()
    }

    fun insertItem(cartItem: CartItem) {
        InsertAsyncTask(cartDao).execute(cartItem)
    }

    fun updateItem(cartItem: CartItem) {
        UpdateAsyncTask(cartDao).execute(cartItem)
    }

    fun deleteItem(cartItem: CartItem) {
        DeleteAsyncTask(cartDao).execute(cartItem)
    }

    fun deleteAllItems(){
        DeleteAllDataAsyncTask(cartDao).execute()
    }

    companion object {
        class InsertAsyncTask(private val cartDao: CartDao) :
            AsyncTask<CartItem, Void, Void>() {
            override fun doInBackground(vararg p0: CartItem?): Void? {
                cartDao.insertItem(p0[0]!!)
                return null
            }

        }

        class UpdateAsyncTask(private val cartDao: CartDao) :
            AsyncTask<CartItem, Void, Void>() {
            override fun doInBackground(vararg p0: CartItem?): Void? {
                cartDao.updateItem(p0[0]!!)
                return null
            }
        }

        class DeleteAsyncTask(private val cartDao: CartDao) :
            AsyncTask<CartItem, Void, Void>() {
            override fun doInBackground(vararg p0: CartItem?): Void? {
                cartDao.deleteItem(p0[0]!!)
                return null
            }
        }

        class DeleteAllDataAsyncTask(private val cartDao: CartDao) :
            AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                cartDao.deleteAllCartData()
                return null
            }

        }
    }
}