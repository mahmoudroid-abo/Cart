package com.mahmoudroid.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudroid.cart.databinding.CartListItemBinding
import com.mahmoudroid.cart.model.CartItem

class CartAdapter(
    private val onAddClickListener: OnAddClickListener
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    interface OnAddClickListener {
        fun onAddClickListener(cartItem: CartItem) {
        }
    }
    private var cartList: List<CartItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartListItem = cartList[position]
        holder.setData(cartListItem.itemName, position)
        holder.setListeners()
    }

    fun setCartListItem(items: List<CartItem>?) {
        cartList = items!!
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = cartList.size


    inner class CartViewHolder(val binding: CartListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var pos: Int = 0
        fun setData(
            itemName: String,
            position: Int,
        ) {
            binding.itemName.text = itemName
            this.pos = position
        }

        fun setListeners() {
            binding.addItem.setOnClickListener {
                onAddClickListener.onAddClickListener(cartList[pos])
            }
        }
    }
}