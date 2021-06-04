package com.devstart.protoenergy.orders.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devstart.protoenergy.databinding.OrderItemBinding
import com.devstart.protoenergy.orders.model.Order
import com.devstart.protoenergy.util.DateConverter
import java.util.*

class OrderAdapter(private val clickListener: OnClickListener): ListAdapter<Order, OrderAdapter.OrderViewHolder>(OrderDiffUtil) {

    private var unfilteredList = listOf<Order>()
    inner class OrderViewHolder(private val binding: OrderItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Order) {
            binding.name.text = item.customerName
            binding.location.text = item.deliveryPointName
            binding.date.text = DateConverter.convertData(item.dateCreated).toString()
            binding.status.text = item.status
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(OrderItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            clickListener.onClick(item)
        }
    }

    fun modifyList(list : List<Order>) {
        unfilteredList = list
        submitList(list)
    }

    fun filter(query: CharSequence?) {
        val list = mutableListOf<Order>()

        if(!query.isNullOrEmpty()) {
            list.addAll(unfilteredList.filter {
                it.status.toLowerCase(Locale.getDefault()).contains(query.toString().toLowerCase(Locale.getDefault())) ||
                it.customerName.toLowerCase(Locale.getDefault()).contains(query.toString().toLowerCase(Locale.getDefault())) ||
                it.deliveryPointName.toLowerCase(Locale.getDefault()).contains(query.toString().toLowerCase(Locale.getDefault()))
            })
        } else {
            list.addAll(unfilteredList)
        }

        submitList(list)
    }

    object OrderDiffUtil: DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return newItem.id == oldItem.id
        }
    }

    class OnClickListener(val clickListener: (order : Order) -> Unit) {
        fun onClick(order: Order) = clickListener(order)
    }

}