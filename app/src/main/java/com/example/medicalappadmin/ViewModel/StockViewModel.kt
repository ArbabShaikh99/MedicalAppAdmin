package com.example.medicalappadmin.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalappadmin.FunctionResponseState.stock.AddStockUserState
import com.example.medicalappadmin.FunctionResponseState.stock.GetAllStockState
import com.example.medicalappadmin.Repo.Repo
import com.example.medicalappadmin.State.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel  @Inject constructor( private val repo :Repo): ViewModel(){

    private  val _getAllStockState = MutableStateFlow(GetAllStockState())
    val getAllStockState = _getAllStockState.asStateFlow()

    private val _addUserStockState = MutableStateFlow(AddStockUserState())
    val addUserStockState = _addUserStockState.asStateFlow()

    suspend fun getAllStock(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getAllStock().collect{
                when(it){
                    is State.loading -> {
                        _getAllStockState.value = GetAllStockState(loading = true)
                    }
                    is State.Success ->{
                        _getAllStockState.value = GetAllStockState(loading = false, data = it.data)
                    }
                    is State.Error -> {
                        _getAllStockState.value  = GetAllStockState(Error = it.message, loading = false)
                    }
                }
            }
        }
    }


    fun addOrderInUserStock(userId : String, orderId : String, productId : String,
        productName : String, userName : String, certified : Boolean,
        productStock : Int, productPrice : Int, productCategory : String
    ){
        viewModelScope
            .launch {
                repo.addOrderInUserStock(userId = userId, orderId = orderId, productId = productId,
                    productName = productName, userName = userName, certified = certified,
                    productStock = productStock, productPrice = productPrice, productCategory = productCategory
                ).collect{
                    when(it){
                        is State.loading -> {
                            _addUserStockState.value = AddStockUserState(loading = true)
                        }
                        is State.Success -> {
                            _addUserStockState.value = AddStockUserState(data = it.data)
                        }
                        is State.Error -> {
                            _addUserStockState.value = AddStockUserState(error = it.message)
                        }
                    }
                }
            }
    }
    fun resetAddStockUserState(){
        _addUserStockState.value = AddStockUserState()
    }
}