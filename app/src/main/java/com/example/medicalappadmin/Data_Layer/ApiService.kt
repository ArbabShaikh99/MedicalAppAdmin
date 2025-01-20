package com.example.medicalappadmin.Data_Layer

import com.example.medicalappadmin.Data_Layer.Response.Product.AddProductResponse
import com.example.medicalappadmin.Data_Layer.Response.Product.ProductModelItem
import com.example.medicalappadmin.Data_Layer.Response.User.GetAllUserResponseItem
import com.example.medicalappadmin.Data_Layer.Response.MessageStatusResponse
import com.example.medicalappadmin.Data_Layer.Response.Product.getSpecificroductResponseItem
import com.example.medicalappadmin.Data_Layer.Response.SellHistory.SellHistoryResponseItem
import com.example.medicalappadmin.Data_Layer.Response.User.UpdateUserResponse
import com.example.medicalappadmin.Data_Layer.Response.order.getAllOrderResponseItem
import com.example.medicalappadmin.Data_Layer.Response.stock.getAllStockResponseItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

 // Users Routes

    @GET("/getAllUsers")
    suspend fun getAllUser():  Response<ArrayList<GetAllUserResponseItem>>

    @FormUrlEncoded
    @PATCH("/UpDateUserDetails")
    suspend fun updateUserApprove(
        @Field("userID") userID :String,
        @Field("isApproved") isApproved : Int,
    ):Response<UpdateUserResponse>

    @FormUrlEncoded
    @PATCH("/UpDateUserDetails")
    suspend fun updateBlockUser(
        @Field("userID") userID :String,
        @Field("block") block : Int
    ) :Response<UpdateUserResponse>

    @DELETE("/DeleteUser")
    suspend fun deleteSpecificUser(
        @Query("UserID") userId: String
    ): Response<MessageStatusResponse>



    // Order Routes

    @GET("/getAllOrders")
    suspend fun getAllOrder():Response<ArrayList<getAllOrderResponseItem>>

    @FormUrlEncoded
    @POST("/getSpecificOrder")
    suspend fun  getSpecificOrders(
        @Field("order_id") orderId:String
    ): Response<ArrayList<getAllOrderResponseItem>>

    @FormUrlEncoded
    @PATCH("/updateOrder")
    suspend fun updateOrderApprove(
        @Field("orderId") orderId :String,
        @Field("isApproved") orderApproved : Int,
        @Field("order_status") orderStatusStep : String

    ): Response<MessageStatusResponse>

    @FormUrlEncoded
    @PATCH("updateOrder")
    suspend fun updateCancelledOrder(
        @Field("orderId") orderId : String,
        @Field("order_cancel_status") orderCancelStatus : String,
        @Field("order_status") orderStatusStep : String
    ) : Response<MessageStatusResponse>

    @FormUrlEncoded
    @PATCH("/updateOrder")
    suspend fun updateShippedOrder(
        @Field("orderId") orderId : String,
        @Field("shipped_date") shippedDate : String,
        @Field("order_status") orderStatusStep : String
    ) : Response<MessageStatusResponse>

    @FormUrlEncoded
    @PATCH("/updateOrder")
    suspend fun updateOutOfDeliveryOrder(
        @Field("orderId") orderId : String,
        @Field("out_of_delivery_date") outOfDeliveryDate : String,
        @Field("order_status") orderStatusStep : String
    ) : Response<MessageStatusResponse>

    @FormUrlEncoded
    @PATCH("/updateOrder")
    suspend fun updateDeliveredOrder(
        @Field("orderId") orderId : String,
        @Field("delivered_date") deliveredDate : String,
        @Field("order_status") orderStatusStep : String
    ) : Response<MessageStatusResponse>


    // Product Routes

    @Multipart
    @POST("/addProduct")
    suspend fun addProduct(
        @Part("product_name") productName: RequestBody,
        @Part("product_category") productCategory: RequestBody,
        @Part("product_price") productPrice: RequestBody,
        @Part("product_stock") productStock: RequestBody,
        @Part("product_expiry_date") productExpiryDate: RequestBody,
        @Part("product_rating") productRating: RequestBody,
        @Part("product_description") productDescription: RequestBody,
        @Part pic: MultipartBody.Part,
        @Part("product_power") productPower: RequestBody
    ): Response<AddProductResponse>

    @GET("/getProduct")
    suspend fun getAllProduct() : Response<ArrayList<ProductModelItem>>


    @FormUrlEncoded
    @POST("/getSpecificProduct")
    suspend fun getSpecificProduct(
     @Field("ProductID") productId : String
    ) : Response<ArrayList<getSpecificroductResponseItem>>

    @FormUrlEncoded
    @PATCH("/updateProducts")
    suspend fun updateStockProduct(
        @Field("ProductID") productId: String,
        @Field("product_stock") productStock: Int
    ): Response<MessageStatusResponse>


    // Stock Routes

    @GET("/getAllStock")
    suspend fun getAllStock(): Response<ArrayList<getAllStockResponseItem>>

    @FormUrlEncoded
    @POST("/stock")
    suspend fun addOrderInUserStock(
        @Field("user_id") userId: String,
        @Field("order_id") orderId: String,
        @Field("product_id") productId: String,
        @Field("product_name") productName: String,
        @Field("user_name") userName: String,
        @Field("certified") certified: Boolean,
        @Field("stock") productStock: Int,
        @Field("price") productPrice: Int,
        @Field("product_category") productCategory: String
    ) : Response<MessageStatusResponse>



    // Sell History Routes

    @FormUrlEncoded
    @POST("/sell_history")
    suspend fun addSellHistory(
        @Field("user_id") userId: String,
        @Field("product_id") productId: String,
        @Field("quantity") quantity: String,
        @Field("remaining_stock") remainingStock: String,
        @Field("date_of_sell") dateOfSell: String,
        @Field("total_amount") totalAmount: String,
        @Field("price") productPrice: String,
        @Field("product_name") productName: String,
        @Field("product_category") productCategory: String,
        @Field("user_name") userName: String,
    ): Response<MessageStatusResponse>

    @GET("/getAllSellHistory")
    suspend fun getAllSellHistory(): Response<ArrayList<SellHistoryResponseItem>>

}