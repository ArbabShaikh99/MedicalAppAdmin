package com.example.medicalappadmin.FunctionResponseState.SellHistory

import com.example.medicalappadmin.Data_Layer.Response.SellHistory.SellHistoryResponseItem
import retrofit2.Response

data class GetAllSellHistoryState(
    val loading : Boolean = false,
    val data : Response<ArrayList<SellHistoryResponseItem>>?= null,
    val error : String ?= null
)
