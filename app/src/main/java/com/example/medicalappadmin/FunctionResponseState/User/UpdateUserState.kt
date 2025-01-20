package com.example.medicalappadmin.FunctionResponseState.User

import com.example.medicalappadmin.Data_Layer.Response.User.UpdateUserResponse
import retrofit2.Response

data class UpdateUserState(
    val loading: Boolean=false,
    val Error:String?=null,
    val Data : Response<UpdateUserResponse>? =null
)
