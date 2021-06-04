package com.devstart.protoenergy.orders.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
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
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_orders.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class OrdersFragment : Fragment() {
    @Inject
    lateinit var viewModel: OrderViewModel

    private lateinit var binding: FragmentOrdersBinding
    private lateinit var orderAdapter: OrderAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_orders, container, false)
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if(isConnected) {
           fetchData()
        }else {
            progressBar?.hide()
            view?.snack("Check Your internet connectivity")
        }
        orderAdapter = OrderAdapter(OrderAdapter.OnClickListener {
            navigateToOrderDetail(it)
        })
        binding.recyclerview.adapter = orderAdapter
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
         inflater.inflate(R.menu.menu, menu)
        val search = menu?.findItem(R.id.search_bar)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                orderAdapter.filter(p0)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun fetchData() {
        lifecycleScope.launchWhenCreated {
            viewModel.fetchOrders().collect { res ->
                when(res) {
                    is Failure -> {
                        logFailiure(res.throwable)
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
        orderAdapter.submitList(response)
        orderAdapter.modifyList(response)
        recyclerview.show()
    }

    private fun logFailiure(failure: Throwable) {
        progressBar.hide()
        view?.snack("An error occurred while trying to fetch Orders")
        Log.i("Failure", failure.localizedMessage)
    }

    private fun navigateToOrderDetail(orderDetails: Order) {
        val direction = OrdersFragmentDirections.actionOrdersFragmentToOrderDetailFragment(orderDetails)
        findNavController().navigate(direction)
    }
}