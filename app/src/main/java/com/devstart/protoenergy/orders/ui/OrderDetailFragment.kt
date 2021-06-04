package com.devstart.protoenergy.orders.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.devstart.protoenergy.R
import com.devstart.protoenergy.databinding.FragmentOrderDetailBinding
import com.devstart.protoenergy.orders.model.Order
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderDetailFragment : BottomSheetDialogFragment( ) {

    private lateinit var binding: FragmentOrderDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_order_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val orderObject = bundle?.let { OrderDetailFragmentArgs.fromBundle(it) }
        binding.order = orderObject?.orderDetails

        binding.floatingActionButton.setOnClickListener {
            val value: Order? = orderObject?.orderDetails

            value?.userPhoneNumber0.let {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + value?.userPhoneNumber0))
                startActivity(intent)
            }
        }
    }
}