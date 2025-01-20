package com.example.medicalappadmin.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalappadmin.FunctionResponseState.Order.getSpecisificProductResponseState
import com.example.medicalappadmin.FunctionResponseState.Product.AddProductState
import com.example.medicalappadmin.FunctionResponseState.Product.AddUpdateProductState
import com.example.medicalappadmin.FunctionResponseState.Product.ProductResponseState
import com.example.medicalappadmin.Repo.Repo
import com.example.medicalappadmin.State.State
import com.example.medicalappadmin.UI_Layer.ScreenState.AddProductScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody


@HiltViewModel
class ProductViewModel  @Inject constructor(private val repo: Repo): ViewModel() {

    private val _addProductState = MutableStateFlow(AddProductState())
    val addProductState = _addProductState.asStateFlow()

    private  val _addProductScreenData = MutableStateFlow(AddProductScreenState())
    val addProductScreenData = _addProductScreenData.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(6000),
        initialValue = AddProductScreenState()
    )

    private val _getAllProducts = MutableStateFlow(ProductResponseState())
    val getAllProducts = _getAllProducts.asStateFlow()

    private val _getSpecificProduct = MutableStateFlow(getSpecisificProductResponseState())
    val getSpecificProduct = _getSpecificProduct.asStateFlow()

    private val _updateProductResponseData = MutableStateFlow(AddUpdateProductState())
    val updateProductResponseData = _updateProductResponseData.asStateFlow()

private  val _updateProductScreenData = MutableStateFlow(AddProductScreenState())
  val updateProductScreenData = _updateProductScreenData.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = AddProductScreenState()
  )

    fun AddProduct(
        productName: String, productCategory: String,
        productPrice: Int, productStock: Int,
        productExpiryDate: String, productRating: Float,
        productDescription: String, productImageFile: MultipartBody.Part,
        productPower: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addProduct(
                productName = productName.toRequestBody("text/plain".toMediaTypeOrNull()),
                productCategory = productCategory.toRequestBody("text/plain".toMediaTypeOrNull()),
                productPrice = productPrice.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                productStock = productStock.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                productExpiryDate = productExpiryDate.toRequestBody("text/plain".toMediaTypeOrNull()),
                productRating = productRating.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                productDescription = productDescription.toRequestBody("text/plain".toMediaTypeOrNull()),
                productImage = productImageFile,
                productPower = productPower.toRequestBody("text/plain".toMediaTypeOrNull())
            ).collect {
                when (it) {
                    is State.loading -> {
                        _addProductState.value = AddProductState(loading = true)
                    }

                    is State.Success -> {
                        _addProductState.value =
                            AddProductState(Data = it.data, loading = false)
                    }

                    is State.Error -> {
                        _addProductState.value =
                            AddProductState(Error = it.message, loading = false)
                    }
                }
            }
        }
    }


    fun getAllProducts(){
        viewModelScope.launch (Dispatchers.IO){
            repo.getAllProducts().collect{
                when(it){
                    is State.loading->{
                        _getAllProducts.value = ProductResponseState(loading = true)
                    }
                    is State.Success->{
                        _getAllProducts.value = ProductResponseState(data = it.data , loading = false)
                    }
                    is State.Error->{
                        _getAllProducts.value = ProductResponseState(error = it.message , loading = false)
                    }
                }
            }
        }
    }
    fun getSpecificProductID(productId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getSpecificProduct(productId = productId).collect{
                when(it){
                    is State.loading->{
                        _getSpecificProduct.value = getSpecisificProductResponseState(loading = true)
                    }
                    is State.Success->{
                        _getSpecificProduct.value = getSpecisificProductResponseState(data = it.data, loading = false)
                    }
                    is State.Error->{
                        _getSpecificProduct.value = getSpecisificProductResponseState(error = it.message,loading = false)
                    }
                }
            }
        }
    }

    fun updateStockProductViewModel(
        productId: String,
        productStock: Int
    ){
        viewModelScope.launch {
            repo.updateStockProduct(
                productId = productId,
                productStock = productStock
            ).collect{
                when(it){
                    is State.loading->{
                        _updateProductResponseData.value = AddUpdateProductState(loading = true)
                    }
                    is State.Success-> {
                        _updateProductResponseData.value = AddUpdateProductState(data = it.data)
                    }
                    is State.Error->{
                        _updateProductResponseData.value = AddUpdateProductState(error = it.message)
                    }
                }
            }
        }
    }


    fun resetProductAddScreenState() {

        _addProductScreenData.value = AddProductScreenState(
            productId = mutableStateOf(""),
        productName = mutableStateOf(""),
            productCategory = mutableStateOf(""),
            productStock = mutableStateOf(""),
            productPrice = mutableStateOf(""),
            productExpiryDate = mutableStateOf(""),
         productRating  = mutableStateOf(""),
         productDescription  = mutableStateOf(""),
         productPower = mutableStateOf(""),
         pic  = mutableStateOf(null),
        )

    }

}
