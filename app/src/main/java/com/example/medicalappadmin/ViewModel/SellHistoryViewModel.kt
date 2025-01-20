package com.example.medicalappadmin.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalappadmin.FunctionResponseState.SellHistory.AddHistoryResponseState
import com.example.medicalappadmin.FunctionResponseState.SellHistory.GetAllSellHistoryState
import com.example.medicalappadmin.Repo.Repo
import com.example.medicalappadmin.State.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellHistoryViewModel  @Inject constructor(private val repo : Repo): ViewModel() {


    private val _addInSellHistoryState = MutableStateFlow(AddHistoryResponseState())
    val addInSellHistoryState = _addInSellHistoryState.asStateFlow()

    private val _getAllSellHistoryState = MutableStateFlow(GetAllSellHistoryState())
    val getAllSellHistoryState = _getAllSellHistoryState.asStateFlow()

    fun addInSellHistory(
        userId: String,
        productId: String,
        quantity: String,
        remainingStock: String,
        dateOfSell: String,
        totalAmount: String,
        productPrice: String,
        productName: String,
        productCategory: String,
        userName: String
    ){
        viewModelScope.launch {
            repo.addInSellHistory(
                userId = userId,
                productId = productId,
                quantity = quantity,
                remainingStock = remainingStock,
                dateOfSell = dateOfSell,
                totalAmount = totalAmount,
                productPrice = productPrice,
                productName = productName,
                productCategory = productCategory,
                userName = userName
            ).collect{
                when(it){
                    is State.loading -> {
                        _addInSellHistoryState.value = AddHistoryResponseState(isLoading = true)
                    }
                    is State.Success -> {
                        _addInSellHistoryState.value = AddHistoryResponseState(data = it.data)
                    }
                    is State.Error -> {
                        _addInSellHistoryState.value = AddHistoryResponseState(error = it.message)
                    }
                }
            }
        }
    }

    fun getAllSellHistory(){
        viewModelScope.launch {
            repo.getAllSellHistory().collect {
                when (it) {
                    is State.loading -> {
                        _getAllSellHistoryState.value = GetAllSellHistoryState(loading = true)
                    }
                    is State.Success -> {
                        _getAllSellHistoryState.value = GetAllSellHistoryState(data = it.data)
                    }
                    is State.Error -> {
                        _getAllSellHistoryState.value = GetAllSellHistoryState(error = it.message)
                    }
                }
            }
        }
    }
    fun resetAddInSellHistoryState(){
        _addInSellHistoryState.value = AddHistoryResponseState()
    }
}