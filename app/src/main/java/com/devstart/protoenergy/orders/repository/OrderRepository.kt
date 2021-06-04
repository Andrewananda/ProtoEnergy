package com.devstart.protoenergy.orders.repository


import com.devstart.protoenergy.network.*
import javax.inject.Inject

class OrderRepository @Inject constructor(private val apiService: ApiService) {

     suspend fun getOrders() : ApiResponse {
        return try {
            val request = apiService.getOrdersAsync().await()
            Success(request)
        }catch (t: Throwable) {
            Failure(t)
        }
    }
}