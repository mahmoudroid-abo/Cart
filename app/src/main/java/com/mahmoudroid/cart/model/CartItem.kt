package com.mahmoudroid.cart.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "cart")
@Parcelize
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val itemId: Int,
    val itemName: String
) : Parcelable
