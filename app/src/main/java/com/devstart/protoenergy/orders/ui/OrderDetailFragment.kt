package com.devstart.protoenergy.orders.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.devstart.protoenergy.R
import com.devstart.protoenergy.databinding.FragmentOrderDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderDetailFragment : BottomSheetDialogFragment( ) {

    private lateinit var binding: FragmentOrderDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_order_detail, container, false)
        val bundle = arguments
        val orderDetails = bundle?.let { OrderDetailFragmentArgs.fromBundle(it) }
        binding.order = orderDetails?.orderDetails

        return binding.root
    }
}