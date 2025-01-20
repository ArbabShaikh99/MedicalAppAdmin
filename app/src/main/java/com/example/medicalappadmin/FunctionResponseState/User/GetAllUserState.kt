package com.example.medicalappadmin.FunctionResponseState.User

import com.example.medicalappadmin.Data_Layer.Response.User.GetAllUserResponseItem
import retrofit2.Response

data class GetAllUserState (
    val loading:Boolean = false,
    val Error: String?=null,
    val data: Response<ArrayList<GetAllUserResponseItem>> ?= null,

    )