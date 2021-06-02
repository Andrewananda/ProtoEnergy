package com.devstart.protoenergy.network

sealed class ApiResponse

data class Success<T>(val data: T): ApiResponse()

class Failure(val throwable: Throwable) : ApiResponse()
