package com.example.medicalappadmin.State

import retrofit2.Response

sealed class State<out T>{
    data class Success<out T>(val data :T) :State<T>()
    data class Error(val message :String) :State<Nothing>()
   object loading:State<Nothing>()
}