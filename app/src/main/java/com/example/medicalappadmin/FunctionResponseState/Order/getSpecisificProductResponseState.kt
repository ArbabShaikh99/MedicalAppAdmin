package com.example.medicalappadmin.FunctionResponseState.Order

import com.example.medicalappadmin.Data_Layer.Response.Product.getSpecificroductResponseItem
import retrofit2.Response



data class getSpecisificProductResponseState(
    val loading : Boolean = false,
    val data : Response<ArrayList<getSpecificroductResponseItem>>? = null,
    val error : String ?= null
)