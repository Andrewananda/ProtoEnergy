package com.devstart.protoenergy.orders.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devstart.protoenergy.network.ApiResponse
import com.devstart.protoenergy.orders.repository.OrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrderViewModel @Inject constructor(private val orderRepository: OrderRepository): ViewModel() {
    private val orderResponse = MutableLiveData<ApiResponse>()
    fun getOrders() : LiveData<ApiResponse> =  orderResponse

    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        coroutineScope.launch {
            fetchOrdersList()
        }
    }

    private suspend fun fetchOrdersList() {
        orderRepository.fetchOrders()
        val data = orderRepository.orders
        orderResponse.value = data.value
    }
}