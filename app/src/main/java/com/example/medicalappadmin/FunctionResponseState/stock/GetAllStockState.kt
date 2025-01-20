package com.example.medicalappadmin.FunctionResponseState.stock

import com.example.medicalappadmin.Data_Layer.Response.stock.getAllStockResponseItem
import retrofit2.Response

data class GetAllStockState (
    val loading:Boolean = false,
    val Error: String?=null,
    val data: Response<ArrayList<getAllStockResponseItem>>?= null,
)