package com.devstart.protoenergy.orders.model

data class Order(
    val customerCode: String,
    val customerName: String,
    val deliveryPointCode: String,
    val deliveryPointName: String,
    val salesAreaCode: String,
    val salesAreaName: String,
    val batchNumber: String,
    val remarks: String?,
    val status: String,
    val orderTotal: Double,
    val userPhoneNumber0: String,
    val id: String,
    val dateCreated: String,
    val createdBy: String,
    val creatorUserEmail: String,
    val dateModified: String?,
    val modifiedBy: String?,
    val modifierUserEmail: String?
)