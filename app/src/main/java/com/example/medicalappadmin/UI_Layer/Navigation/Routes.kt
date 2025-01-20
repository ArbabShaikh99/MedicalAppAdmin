package com.example.medicalappadmin.UI_Layer.Navigation

import kotlinx.serialization.Serializable


@Serializable
data object ApproveOrderScreenRoute {
    const  val route: String = "approve_order"
}


@Serializable
data class OrderDetailScreenRoute(
    val orderId : String
) {
    val route: String = "order_details/$orderId"
}

@Serializable
object OrderApproveroutes

@Serializable
object BottomNavigationRoute

@Serializable
object DashBoardRoutes

@Serializable
object StockUserRoutes

@Serializable
data class ProductDetailRoute(
    val productId : String
){
    val route :String = "product_detail/$productId"
}

@Serializable
object  ShowHistoryScreenRoute

@Serializable
object AddProductRoute