package com.example.medicalappadmin.FunctionResponseState.SellHistory

import com.example.medicalappadmin.Data_Layer.Response.MessageStatusResponse
import retrofit2.Response

data class AddHistoryResponseState(
    val isLoading: Boolean = false,
    val data: Response<MessageStatusResponse>? = null,
    val error: String ?= null
)