package com.devstart.protoenergy.orders.viewModel

import androidx.lifecycle.ViewModel
import com.devstart.protoenergy.orders.repository.OrderRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class OrderViewModel @Inject constructor(private val orderRepository: OrderRepository): ViewModel() {

    suspend fun fetchOrders() = flow {
        emit(orderRepository.getOrders())
    }
}