package com.devstart.protoenergy.orders.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Order(
    @Json(name = "customerCode") val customerCode: String,
    @Json(name = "customerName") val customerName: String,
    @Json(name = "deliveryPointCode") val deliveryPointCode: String,
    @Json(name = "deliveryPointName") val deliveryPointName: String,
    @Json(name = "salesAreaCode") val salesAreaCode: String,
    @Json(name = "salesAreaName") val salesAreaName: String,
    @Json(name = "batchNumber") val batchNumber: String,
    @Json(name = "remarks") val remarks: String?,
    @Json(name = "status") val status: String,
    @Json(name = "orderTotal") val orderTotal: Double,
    @Json(name = "userPhoneNumber0") val userPhoneNumber0: String,
    @Json(name = "id") val id: String,
    @Json(name = "dateCreated") val dateCreated: String,
    @Json(name = "createdBy") val createdBy: String,
    @Json(name = "creatorUserEmail") val creatorUserEmail: String,
    @Json(name = "dateModified") val dateModified: String?,
    @Json(name = "modifiedBy") val modifiedBy: String?,
    @Json(name = "modifierUserEmail") val modifierUserEmail: String?
): Parcelable