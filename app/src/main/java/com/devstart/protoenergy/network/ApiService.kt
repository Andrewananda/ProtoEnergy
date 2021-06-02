package com.devstart.protoenergy.network

import com.devstart.protoenergy.orders.model.Order
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {
    @GET("orders.json")
    fun getOrdersAsync() : Deferred<List<Order>>
}