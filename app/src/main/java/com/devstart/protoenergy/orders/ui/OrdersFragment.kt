package com.devstart.protoenergy.orders.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.devstart.protoenergy.R
import com.devstart.protoenergy.databinding.FragmentOrdersBinding
import com.devstart.protoenergy.network.ApiResponse
import com.devstart.protoenergy.network.Failure
import com.devstart.protoenergy.network.Success
import com.devstart.protoenergy.orders.viewModel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class OrdersFragment : Fragment() {
    @Inject
    lateinit var viewModel: OrderViewModel

    private lateinit var binding: FragmentOrdersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fetchData()
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_orders, container, false)
        return binding.root
    }

    private fun fetchData() {
        lifecycleScope.launchWhenCreated {
            viewModel.fetchOrders().collect { res ->
                when(res) {
                    is Failure -> {
                        logFailiure(res.throwable)
                    }
                    is Success<*> -> {
                        logSuccess(res)
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