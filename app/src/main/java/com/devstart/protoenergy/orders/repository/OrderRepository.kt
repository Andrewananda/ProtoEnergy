package com.devstart.protoenergy.orders.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devstart.protoenergy.network.ApiResponse
import com.devstart.protoenergy.network.ApiService
import com.devstart.protoenergy.network.Failure
import com.devstart.protoenergy.network.Success
import javax.inject.Inject

class OrderRepository @Inject constructor(private val apiService: ApiService) {
    private val orderMutableData = MutableLiveData<ApiResponse>()
    val orders: LiveData<ApiResponse>
    get() = orderMutableData

    suspend fun fetchOrders() {
        val request = apiService.getOrdersAsync().await()
        try {
            orderMutableData.value = Success(request)
        }catch (t: Throwable) {
            Failure(t)
        }
    }

}