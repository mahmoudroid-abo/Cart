package com.mahmoudroid.cart.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mahmoudroid.cart.model.CartItem

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(cartItem: CartItem)

    @Delete
    fun deleteItem(cartItem: CartItem)

    @Query("SELECT * FROM cart")
    fun getAllItems(): LiveData<List<CartItem>>

    @Update
    fun updateItem(cartItem: CartItem)

    @Query("DELETE FROM cart")
    fun deleteAllCartData()
}