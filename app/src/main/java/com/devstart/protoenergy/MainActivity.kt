package com.devstart.protoenergy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.devstart.protoenergy.network.ApiResponse
import com.devstart.protoenergy.network.Failure
import com.devstart.protoenergy.network.Success
import com.devstart.protoenergy.orders.viewModel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()
    }

    private fun fetchData() {
        lifecycleScope.launch {
            viewModel.fetchOrders().collect {
                when(it) {
                    is Failure -> {
                        logFailiure(it.throwable)
                    }
                    is Success<*> -> {
                        logSuccess(it)
                    }
                }
            }
        }
    }

    private fun logSuccess(response: ApiResponse) {
        Log.i("Success", response.toString())
    }

    private fun logFailiure(failure: Throwable) {
        Log.i("Failure", failure.localizedMessage)
    }
}