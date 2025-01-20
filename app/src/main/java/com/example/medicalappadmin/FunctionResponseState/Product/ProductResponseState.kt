package com.example.medicalappadmin.FunctionResponseState.Product

import com.example.medicalappadmin.Data_Layer.Response.Product.ProductModelItem
import retrofit2.Response


data class ProductResponseState(
    val loading : Boolean = false,
    val data : Response<ArrayList<ProductModelItem>>? = null,
    val error : String ?= null
)