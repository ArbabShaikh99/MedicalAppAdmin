package com.example.medicalappadmin.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalappadmin.FunctionResponseState.Order.GetAllOrderResponseState
import com.example.medicalappadmin.FunctionResponseState.Order.UpdateOrderState
import com.example.medicalappadmin.Repo.Repo
import com.example.medicalappadmin.State.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderViewModel @Inject constructor( private val  repo :Repo) : ViewModel() {

    private val _getAllOrderState = MutableStateFlow(GetAllOrderResponseState())
    val getAllOrderState = _getAllOrderState.asStateFlow()

    private val _getSpecificOrderState = MutableStateFlow(GetAllOrderResponseState())
    val getSpecificOrderState = _getSpecificOrderState.asStateFlow()

    private val _orderApproveState = MutableStateFlow(UpdateOrderState())
    val orderApproveState = _orderApproveState.asStateFlow()

    private val _updateCancelledOrderState = MutableStateFlow(UpdateOrderState())
    val updateCancelledOrderState = _updateCancelledOrderState.asStateFlow()

    private val _updateShippedOrderState = MutableStateFlow(UpdateOrderState())
    val updateShippedOrderState = _updateShippedOrderState.asStateFlow()

    private val _updateOutOfDeliveryOrderState = MutableStateFlow(UpdateOrderState())
    val updateOutOfDeliveryOrderState = _updateOutOfDeliveryOrderState.asStateFlow()

    private val _updateDeliveredOrderState = MutableStateFlow(UpdateOrderState())
    val updateDeliveredOrderState = _updateDeliveredOrderState.asStateFlow()

    init {
        getAllOrder()
    }


    fun getAllOrder() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllOrder().collect { state ->
                when (state) {
                    is State.loading -> {
                        _getAllOrderState.value = GetAllOrderResponseState(loading = true)
                    }

                    is State.Success -> {
                        _getAllOrderState.value = GetAllOrderResponseState(
                            Data = state.data,
                            loading = false
                        )
                    }

                    is State.Error -> {
                        _getAllOrderState.value =
                            GetAllOrderResponseState(Error = state.message, loading = false)
                    }
                }

            }
        }
    }

    fun getSpecificOrderViewModel(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getSpecificOrdersRepo(userId).collect { state ->
                when (state) {
                    is State.loading -> {
                        _getSpecificOrderState.value = GetAllOrderResponseState(loading = true)
                    }

                    is State.Success -> {
                        _getSpecificOrderState.value =
                            GetAllOrderResponseState(Data = state.data, loading = false)
                    }

                    is State.Error -> {
                        _getSpecificOrderState.value =
                            GetAllOrderResponseState(Error = state.message, loading = false)
                    }
                }
            }
        }
    }

    fun orderApproveViewModel(orderId: String, orderApprove: Int ,orderStatusStep : String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.OrderApprovedRepo(orderId, orderApprove ,orderStatusStep).collect { state ->
                when (state) {
                    is State.loading -> {
                        _orderApproveState.value = UpdateOrderState(loading = true)
                    }
                    is State.Success -> {
                        _orderApproveState.value =
                            UpdateOrderState(Data = state.data, loading = false)
                    }

                    is State.Error -> {
                        _orderApproveState.value =
                            UpdateOrderState(error = state.message, loading = false)
                    }
                }

            }
        }
    }

    fun updateCancelledStatusOrderViewModel(orderId : String , cancelStatus : String,orderStatus : String){
        viewModelScope.launch {
            repo.updateCancelOrderViewModel(
                orderId = orderId,
                cancelStatus = cancelStatus,
                orderStatus = orderStatus
            ).collect{
                when(it){
                    is State.loading -> {
                        _updateCancelledOrderState.value = UpdateOrderState(loading = true)
                    }
                    is State.Success -> {
                        _updateCancelledOrderState.value = UpdateOrderState(Data = it.data)
                    }
                    is State.Error -> {
                        _updateCancelledOrderState.value = UpdateOrderState(error = it.message)
                    }
                }
            }
        }
    }

    fun updateShippedDateOrder(orderId : String , shippedDate : String,orderStatusStep : String){
        viewModelScope.launch {
            repo.updateShippedOrder(
                orderId = orderId,
                shippedDate = shippedDate,
                orderStatusStep = orderStatusStep
            ).collect{
                when(it){
                    is State.loading -> {
                        _updateShippedOrderState.value = UpdateOrderState(loading = true)
                    }
                    is State.Success -> {
                        _updateShippedOrderState.value = UpdateOrderState(Data = it.data)
                    }
                    is State.Error -> {
                        _updateShippedOrderState.value = UpdateOrderState(error = it.message)
                    }
                }
            }
        }
    }

    fun updateOutOfDeliveryDateOrder(orderId : String , outOfDeliveryDate : String,orderStatusStep : String){
        viewModelScope.launch {
            repo.updateOutOfDeliveryOrder(
                orderId = orderId,
                outOfDeliveryDate = outOfDeliveryDate,
                orderStatusStep = orderStatusStep
            ).collect{
                when(it){
                    is State.loading -> {
                        _updateOutOfDeliveryOrderState.value = UpdateOrderState(loading = true)
                    }
                    is State.Success -> {
                        _updateOutOfDeliveryOrderState.value = UpdateOrderState(Data = it.data)
                    }
                    is State.Error -> {
                        _updateOutOfDeliveryOrderState.value = UpdateOrderState(error = it.message)
                    }
                }
            }
        }
    }

    fun updateDeliveredDateOrder(orderId : String , deliveredDate : String,orderStatusStep : String){
        viewModelScope.launch {
            repo.updateDeliveredOrder(
                orderId = orderId,
                deliveredDate = deliveredDate,
                orderStatusStep = orderStatusStep
            ).collect{
                when(it){
                    is State.loading -> {
                        _updateDeliveredOrderState.value = UpdateOrderState(loading = true)
                    }
                    is State.Success -> {
                        _updateDeliveredOrderState.value = UpdateOrderState(Data = it.data)
                    }
                    is State.Error -> {
                        _updateDeliveredOrderState.value = UpdateOrderState(error = it.message)
                    }
                }
            }
        }
    }

    fun resetUpdateOrderState() {
        _orderApproveState.value = UpdateOrderState()
        _updateShippedOrderState.value = UpdateOrderState()
        _updateOutOfDeliveryOrderState.value = UpdateOrderState()
        _updateDeliveredOrderState.value = UpdateOrderState()

    }
}



