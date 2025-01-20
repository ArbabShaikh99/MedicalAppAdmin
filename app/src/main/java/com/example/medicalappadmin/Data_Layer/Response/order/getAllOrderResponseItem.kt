package com.example.medicalappadmin.Data_Layer.Response.order

data class getAllOrderResponseItem(
    val delivered_date: String,
    val delivery_charge: Double,
    val discount_price: String,
    val id: Int,
    val isApproved: Int,
    val order_cancel_status: String,
    val order_date: String,
    val order_id: String,
    val order_status: String,
    val out_of_delivery_date: String,
    val product_category: String,
    val product_id: String,
    val product_image_id: String,
    val product_name: String,
    val product_price: Int , // int
    val product_quantity: Int,
    val shipped_date: String,
    val subtotal_price: Double,
    val tax_charge: Double,
    val totalPrice: Double,
    val user_address: String,
    val user_city: String,
    val user_email: String,
    val user_id: String,
    val user_mobile: String,
    val user_name: String,
    val user_pinCode: String,
    val user_state: String,
    val user_street: String
)