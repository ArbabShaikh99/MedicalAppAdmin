package com.example.medicalappadmin.UI_Layer.ScreenState

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class AddProductScreenState(
    val productId: MutableState<String> = mutableStateOf(""),
    val productName:MutableState<String> = mutableStateOf(""),
    val productCategory:MutableState<String> = mutableStateOf(""),
    val productStock: MutableState<String> = mutableStateOf(""),
    val productPrice: MutableState<String> = mutableStateOf(""),
    val productExpiryDate:MutableState<String> = mutableStateOf(""),
    val productRating :MutableState<String> = mutableStateOf(""),
    val productDescription :MutableState<String> = mutableStateOf(""),
    val productPower :MutableState<String> = mutableStateOf(""),
    val pic :MutableState<Uri?> = mutableStateOf(null)
    )

