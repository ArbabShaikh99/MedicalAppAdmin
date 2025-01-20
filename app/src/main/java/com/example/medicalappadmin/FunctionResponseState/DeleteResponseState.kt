package com.example.medicalappadmin.FunctionResponseState

import com.example.medicalappadmin.Data_Layer.Response.MessageStatusResponse
import retrofit2.Response

data class DeleteResponseState(
    val isLoading: Boolean = false,
    val data: Response<MessageStatusResponse>? = null,
    val error: String ?= null
)