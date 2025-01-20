package com.example.medicalappadmin.FunctionResponseState.Product

import com.example.medicalappadmin.Data_Layer.Response.Product.AddProductResponse
import retrofit2.Response

data class AddProductState(
    val loading: Boolean=false,
    val Error:String?=null,
    val Data : Response<AddProductResponse>? =null
)
