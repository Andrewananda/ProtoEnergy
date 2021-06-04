package com.devstart.protoenergy.orders.viewModel

import androidx.lifecycle.ViewModel
import com.devstart.protoenergy.orders.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val orderRepository: OrderRepository): ViewModel() {

     fun fetchOrders() = flow {
        emit(orderRepository.getOrders())
    }
}