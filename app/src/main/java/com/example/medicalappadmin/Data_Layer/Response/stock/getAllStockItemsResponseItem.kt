package com.example.medicalappadmin.Data_Layer.Response.stock

data class getAllStockResponseItem(
    val certified: String,
    val id: Int,
    val order_id: String,
    val product_category: String,
    val product_id: String,
    val product_name: String,
    val product_price: Int,
    val product_stock: Int,
    val user_id: String,
    val user_name: String
)