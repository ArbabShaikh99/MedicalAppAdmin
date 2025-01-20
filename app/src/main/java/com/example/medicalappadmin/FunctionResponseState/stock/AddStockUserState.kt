package com.example.medicalappadmin.FunctionResponseState.stock

import com.example.medicalappadmin.Data_Layer.Response.MessageStatusResponse
import retrofit2.Response

data class AddStockUserState(
    val loading : Boolean = false,
    val data : Response<MessageStatusResponse>?= null,
    val error : String ?= null
)
