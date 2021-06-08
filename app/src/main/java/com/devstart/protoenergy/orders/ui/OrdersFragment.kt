package com.devstart.protoenergy.orders.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.devstart.protoenergy.R
import com.devstart.protoenergy.databinding.FragmentOrdersBinding
import com.devstart.protoenergy.network.Failure
import com.devstart.protoenergy.network.Success
import com.devstart.protoenergy.orders.model.Order
import com.devstart.protoenergy.orders.viewModel.OrderViewModel
import com.devstart.protoenergy.util.hide
import com.devstart.protoenergy.util.show
import com.devstart.protoenergy.util.snack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_orders.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class OrdersFragment : Fragment() {

     val viewModel: OrderViewModel by viewModels()

    private lateinit var binding: FragmentOrdersBinding
    private lateinit var orderAdapter: OrderAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if(isConnected) {
            fetchData()
        }else {
            progressBar?.hide()
            textHolder.text = "Check Your internet connectivity, Click To Retry"
            textHolder.setOnClickListener {
                fetchData()
            }
            textHolder.show()
            view?.snack("Check Your internet connectivity")
        }
        orderAdapter = OrderAdapter(OrderAdapter.OnClickListener {
            navigateToOrderDetail(it)
        })
        binding.recyclerview.adapter = orderAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val minflater: MenuInflater = inflater
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, minflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.all -> {
                orderAdapter.filter("")
            }
            else -> {
                 orderAdapter.filter(item.title)
                super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    private fun fetchData() {
        progressBar.show()
        textHolder.hide()
        lifecycleScope.launchWhenCreated {
            viewModel.fetchOrders().collect { res ->
                when(res) {
                    is Failure -> {
                        logFailure(res.throwable)
                    }
                    is Success<*> -> {
                        bindView(res.data as List<Order>)
                    }
                }
            }
        }
    }

    private fun bindView(response: List<Order>) {
        progressBar.hide()
        orderAdapter.modifyList(response)
        recyclerview.show()
    }

    private fun logFailure(failure: Throwable) {
        progressBar.hide()
        textHolder.text = "An error occurred while trying to fetch Orders"
        textHolder.show()
        textHolder.setOnClickListener {
            fetchData()
        }
        view?.snack("An error occurred while trying to fetch Orders")
        Log.i("Failure", failure.localizedMessage)
    }

    private fun navigateToOrderDetail(orderDetails: Order) {
        val direction = OrdersFragmentDirections.actionOrdersFragmentToOrderDetailFragment(orderDetails)
        findNavController().navigate(direction)
    }


}