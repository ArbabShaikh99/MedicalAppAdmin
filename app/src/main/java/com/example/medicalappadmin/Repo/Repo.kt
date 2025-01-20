package com.example.medicalappadmin.Repo

import android.util.Log
import com.example.medicalappadmin.Data_Layer.ApiService
import com.example.medicalappadmin.Data_Layer.Response.Product.AddProductResponse
import com.example.medicalappadmin.Data_Layer.Response.Product.ProductModelItem
import com.example.medicalappadmin.Data_Layer.Response.User.GetAllUserResponseItem
import com.example.medicalappadmin.Data_Layer.Response.MessageStatusResponse
import com.example.medicalappadmin.Data_Layer.Response.Product.getSpecificroductResponseItem
import com.example.medicalappadmin.Data_Layer.Response.SellHistory.SellHistoryResponseItem
import com.example.medicalappadmin.Data_Layer.Response.User.UpdateUserResponse
import com.example.medicalappadmin.Data_Layer.Response.order.getAllOrderResponseItem
import com.example.medicalappadmin.Data_Layer.Response.stock.getAllStockResponseItem
import com.example.medicalappadmin.State.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class Repo @Inject constructor(
    private val apiServices: ApiService) {


    ////////////             User  Repository /////////////////

     suspend fun getAllUserRepo():Flow<State<Response<ArrayList<GetAllUserResponseItem>>>> = flow {
        emit(State.loading)
         try {
             val response = apiServices.getAllUser()
             emit(State.Success(response))
         }
         catch (e:Exception){
             emit(State.Error(e.message.toString()))
         }
    }


    suspend fun  approveUser(userID:String ,isApproved:Int):
            Flow<State<Response<UpdateUserResponse>>> = flow {

                emit(State.loading)
        try {
            val response = apiServices.updateUserApprove(userID = userID , isApproved = isApproved)
            emit(State.Success(response))
        }
        catch (e:Exception){
            emit(State.Error(e.message.toString()))

        }
    }

  suspend fun updateBlockStatus(
    userID:String ,
    isBlock:Int): Flow<State<Response<UpdateUserResponse>>> = flow {
      emit(State.loading)
      try{
          val response =apiServices.updateBlockUser(userID = userID, block = isBlock)
          emit(State.Success(response))
      }
      catch (e:Exception){
          emit(State.Error(e.message.toString()))

      }
    }

  suspend fun deleteSpecificUser(
      userID: String
  ): Flow<State<Response<MessageStatusResponse>>> = flow {
      emit(State.loading)

      try {
          val response =apiServices.deleteSpecificUser(userID)
          emit(State.Success(response))
      }
      catch (e:Exception){
          emit(State.Error(e.message.toString()))
      }
  }


    ////////////             Order  Repository   /////////////////

    suspend fun getAllOrder() :Flow<State<Response<ArrayList<getAllOrderResponseItem>>>> = flow {
        emit(State.loading)

        try {
            val response = apiServices.getAllOrder()
            emit(State.Success(response))

        }
        catch(e:Exception){
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun  getSpecificOrdersRepo(orderId:String):Flow<State<Response<ArrayList<getAllOrderResponseItem>>>> = flow {
   emit(State.loading)
        try {
            val response = apiServices.getSpecificOrders(orderId)
            emit(State.Success(response))
        }
        catch (e:Exception){
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun OrderApprovedRepo(orderId: String, orderApprove :Int,orderStatusStep : String) :Flow<State<Response<MessageStatusResponse>>> = flow {
        emit(State.loading)
        try {
            val response = apiServices.updateOrderApprove(orderId,orderApprove ,orderStatusStep)
            emit(State.Success(response))
        }
        catch (e:Exception){
            emit(State.Error(e.message.toString()))
        }
    }
    suspend fun updateCancelOrderViewModel(orderId: String, cancelStatus: String, orderStatus: String): Flow<State<Response<MessageStatusResponse>>> = flow {
        emit(State.loading)
        try {
            val response = apiServices.updateCancelledOrder(orderId,cancelStatus,orderStatus)
            emit(State.Success(response))
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun updateShippedOrder(orderId: String, shippedDate: String, orderStatusStep: String
    ): Flow<State<Response<MessageStatusResponse>>> = flow {
        emit(State.loading)
        try {
            val response = apiServices.updateShippedOrder(
                orderId = orderId,
                shippedDate = shippedDate,
                orderStatusStep = orderStatusStep
            )
            emit(State.Success(response))
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun updateOutOfDeliveryOrder(
        orderId: String,
        outOfDeliveryDate: String,
        orderStatusStep: String
    ): Flow<State<Response<MessageStatusResponse>>> = flow {
        emit(State.loading)
        try {
            val response = apiServices.updateOutOfDeliveryOrder(
                orderId = orderId,
                outOfDeliveryDate = outOfDeliveryDate,
                orderStatusStep = orderStatusStep
            )
            emit(State.Success(response))
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun updateDeliveredOrder(
        orderId: String,
        deliveredDate: String,
        orderStatusStep: String
    ): Flow<State<Response<MessageStatusResponse>>> = flow {
        emit(State.loading)
        try {
            val response = apiServices.updateDeliveredOrder(
                orderId = orderId,
                deliveredDate = deliveredDate,
                orderStatusStep = orderStatusStep
            )
            emit(State.Success(response))
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }


    ////////////             Stock  Repository   /////////////////


    suspend fun getAllStock():Flow<State<Response<ArrayList<getAllStockResponseItem>>>> = flow {
        emit(State.loading)
        try {
            val response = apiServices.getAllStock()
            emit(State.Success(response))
        }
        catch (e:Exception){
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun updateStockProduct(productId: String, productStock: Int
    ): Flow<State<Response<MessageStatusResponse>>> = flow {
        emit(State.loading)
        try {
            val response = apiServices.updateStockProduct(
                productId = productId,
                productStock = productStock
            )
            emit(State.Success(response))
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun addOrderInUserStock(userId: String, orderId: String,
        productId: String, productName: String, userName: String, certified: Boolean,
        productStock: Int, productPrice: Int, productCategory: String
    ): Flow<State<Response<MessageStatusResponse>>> = flow {
        emit(State.loading)
        try {
            val response = apiServices.addOrderInUserStock(
                userId = userId,
                orderId = orderId,
                productId = productId,
                productName = productName,
                userName = userName,
                certified = certified,
                productStock = productStock,
                productPrice = productPrice,
                productCategory = productCategory
            )
            emit(State.Success(response))
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }




    ////////////             Product Repository    /////////////////

    suspend fun getAllProducts(): Flow<State<Response<ArrayList<ProductModelItem>>>> = flow {
        emit(State.loading)
        try {
            val response = apiServices.getAllProduct()
            emit(State.Success(response))
            // val response = apiService.getSpecificProduct()
            Log.d("@product_detail", "Response getproduct: ${response.body()}")
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun getSpecificProduct(productId: String): Flow<State<Response<ArrayList<getSpecificroductResponseItem>>>> = flow {
        emit(State.loading)
        try {
            val response = apiServices.getSpecificProduct(productId = productId)
            emit(State.Success(response))
            Log.d("@product_detail", "Response: ${response.body()}")
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }


    suspend fun addProduct(
        productName: RequestBody,
        productCategory: RequestBody,
        productPrice: RequestBody,
        productStock: RequestBody,
        productExpiryDate: RequestBody,
        productRating: RequestBody,
        productDescription: RequestBody,
        productImage: MultipartBody.Part,
        productPower: RequestBody
    ):Flow<State<Response<AddProductResponse>>> = flow {

        emit(State.loading)
        try {
            val response = apiServices.addProduct( productName = productName,
                productCategory = productCategory, productPrice = productPrice,
                productStock = productStock, productExpiryDate = productExpiryDate,
                productRating = productRating, productDescription = productDescription,
                pic = productImage, productPower = productPower)
            emit(State.Success(response))
        }

        catch (e:Exception){
            emit(State.Error(e.message.toString()))
        }

    }


    ////////////             Sell History  Repository   /////////////////


    suspend fun addInSellHistory(
        userId: String, productId: String, quantity: String, remainingStock: String, dateOfSell: String, totalAmount: String,
        productPrice: String, productName: String, productCategory: String, userName: String
    ): Flow<State<Response<MessageStatusResponse>>> = flow {
        emit(State.loading)

        try {
            val response = apiServices.addSellHistory(
                userId = userId, productId = productId,
                quantity = quantity, remainingStock = remainingStock,
                dateOfSell = dateOfSell, totalAmount = totalAmount,
                productPrice = productPrice, productName = productName,
                productCategory = productCategory, userName = userName
            )
            emit(State.Success(response))
        } catch (e: Exception) {
            emit(
                State.Error(e.message.toString())
            )
        }
    }

     suspend fun getAllSellHistory(): Flow<State<Response<ArrayList<SellHistoryResponseItem>>>> = flow {
        emit(State.loading)
        try {
            val response = apiServices.getAllSellHistory()
            emit(State.Success(response))
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }
}