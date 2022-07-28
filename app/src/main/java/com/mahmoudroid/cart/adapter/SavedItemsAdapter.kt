package com.mahmoudroid.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudroid.cart.databinding.SavedItemListBinding
import com.mahmoudroid.cart.model.CartItem

class SavedItemsAdapter : RecyclerView.Adapter<SavedItemsAdapter.SavedItemsViewHolder>() {

    private var cartList: List<CartItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedItemsViewHolder {
        val binding = SavedItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SavedItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedItemsViewHolder, position: Int) {
        val cartListItem = cartList[position]
        holder.setData(cartListItem.itemName, position)
    }

    override fun getItemCount(): Int = cartList.size

    fun setCartListItem(items: List<CartItem>?) {
        cartList = items!!
        notifyDataSetChanged()
    }

    inner class SavedItemsViewHolder(val binding: SavedItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var pos: Int = 0
        fun setData(
            itemName: String,
            position: Int,
        ) {
            binding.itemName.text = itemName
            this.pos = position
        }
    }
}