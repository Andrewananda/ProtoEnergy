package com.devstart.protoenergy.orders.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devstart.protoenergy.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderDetailFragment : BottomSheetDialogFragment( ) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        val orderDetails = bundle?.let { OrderDetailFragmentArgs.fromBundle(it) }
        Log.i("Bundle", orderDetails.toString())

        return inflater.inflate(R.layout.fragment_order_detail, container, false)
    }
}