package com.example.medicalappadmin.FunctionResponseState.Order

import com.example.medicalappadmin.Data_Layer.Response.MessageStatusResponse
import retrofit2.Response

data class UpdateOrderState(
    val loading : Boolean = false,
    val Data : Response<MessageStatusResponse>?= null,
    val error : String ?= null
)
