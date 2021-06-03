package com.devstart.protoenergy.orders.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devstart.protoenergy.databinding.OrderItemBinding
import com.devstart.protoenergy.orders.model.Order

class OrderAdapter: ListAdapter<Order, OrderAdapter.OrderViewHolder>(OrderDiffUtil) {

    inner class OrderViewHolder(private val binding: OrderItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Order) {
            binding.name.text = item.customerName
            binding.location.text = item.deliveryPointName
            binding.date.text = item.dateCreated
            binding.status.text = item.status
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(OrderItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    object OrderDiffUtil: DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return newItem.id == oldItem.id
        }

    }

}