package com.example.medicalappadmin.FunctionResponseState.Order

import com.example.medicalappadmin.Data_Layer.Response.order.getAllOrderResponseItem
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response

data class GetAllOrderResponseState(
    val loading:Boolean = false,
    val Error:String?=null,
    val Data: Response<ArrayList<getAllOrderResponseItem>>?= null,
)