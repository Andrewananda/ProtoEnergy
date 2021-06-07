package com.devstart.protoenergy.orders.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devstart.protoenergy.databinding.FragmentOrderDetailBinding
import com.devstart.protoenergy.orders.model.Order
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderDetailFragment : BottomSheetDialogFragment( ) {

    private lateinit var binding: FragmentOrderDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val orderObject = bundle?.let { OrderDetailFragmentArgs.fromBundle(it) }
        val order = orderObject?.orderDetails
        bindView(order)
        binding.floatingActionButton.setOnClickListener {
            val value: Order? = orderObject?.orderDetails

            value?.userPhoneNumber0.let {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + value?.userPhoneNumber0))
                startActivity(intent)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindView(item: Order?) {
        binding.customerName.text = item?.customerName
        binding.batchNumber.text = item?.batchNumber
        binding.salesAreaName.text = item?.salesAreaName
        binding.status.text = item?.status
        binding.orderTotal.text =  "Kes ${item?.orderTotal.toString()}"
    }
}