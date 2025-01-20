package com.example.medicalappadmin.UI_Layer.Screen.Order_Screens

import android.util.Log

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.binayshaw7777.kotstep.model.tabVerticalWithLabel
import com.binayshaw7777.kotstep.ui.vertical.VerticalStepper
import com.example.medicalappadmin.Data_Layer.Response.order.getAllOrderResponseItem
import com.example.medicalappadmin.R
import com.example.medicalappadmin.UI_Layer.Component.ImageComponent
import com.example.medicalappadmin.UI_Layer.Component.TopAppBar
import com.example.medicalappadmin.UI_Layer.Screen.All_User_Screen.ButtonView
import com.example.medicalappadmin.ViewModel.OrderViewModel
import com.example.medicalappadmin.ViewModel.ProductViewModel
import com.example.medicalappadmin.ViewModel.SellHistoryViewModel
import com.example.medicalappadmin.ViewModel.StockViewModel
import com.example.medicalappadmin.ui.theme.GreenColor
import com.example.medicalappadmin.ui.theme.softWhiteColor
import java.util.Locale

@Composable
fun OrderDetailUI(
    orderId:String,
    orderViewModel: OrderViewModel,
    navController: NavController,
    productViewModel: ProductViewModel,
    stockViewModel : StockViewModel,
    sellHistoryViewModel: SellHistoryViewModel
) {



    LaunchedEffect(Unit) {
        orderViewModel.getSpecificOrderViewModel(orderId)
    }

    val getSpecificOrderState = orderViewModel.getSpecificOrderState.collectAsState()
    val ApproveOrderState = orderViewModel.orderApproveState.collectAsState()
    val getCancelledOrderState = orderViewModel.updateCancelledOrderState.collectAsState()
    val getShippedOrderState = orderViewModel.updateShippedOrderState.collectAsState()
    val getOutOfDeliveryOrderState = orderViewModel.updateOutOfDeliveryOrderState.collectAsState()
    val getDeliveredOrderState = orderViewModel.updateDeliveredOrderState.collectAsState()
    val userStockState = stockViewModel.addUserStockState.collectAsState()
    val addSellHistoryState = sellHistoryViewModel.addInSellHistoryState.collectAsState()

//
//


    // get specific product
//    val SpecificProductState = productViewModel.getSpecificProduct.collectAsState()
//    val SpecificProductData = SpecificProductState.value.data?.body()
//
//    val getAllProductResponseState = productViewModel.getAllProducts.collectAsState()
//    val getAllproductData = getAllProductResponseState.value.data?.body() ?: emptyList()

    val context = LocalContext.current
    val lazyState = rememberLazyListState()
    val scrollSate = rememberScrollState()

    when {
        ApproveOrderState.value.loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
        }

        ApproveOrderState.value.Data != null -> {
            LaunchedEffect(Unit) {
                orderViewModel.getSpecificOrderViewModel(orderId)
            }
            orderViewModel.resetUpdateOrderState()

            Toast.makeText(
                context,
                ApproveOrderState.value.Data!!.body()?.message,
                Toast.LENGTH_SHORT
            ).show()
        }

        ApproveOrderState.value.error != null -> {
            Toast.makeText(context, ApproveOrderState.value.error, Toast.LENGTH_SHORT).show()

        }
    }
    when {
        getShippedOrderState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        getShippedOrderState.value.Data != null -> {

            LaunchedEffect(Unit) {
                orderViewModel.getSpecificOrderViewModel(orderId)
            }
            orderViewModel.resetUpdateOrderState()
            Toast.makeText(context,
                getShippedOrderState.value.Data!!.body()?.message, Toast.LENGTH_SHORT
            ).show()
        }

        getShippedOrderState.value.error != null -> {
            Toast.makeText(context, "${getShippedOrderState.value.error}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    when {
        getOutOfDeliveryOrderState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        getOutOfDeliveryOrderState.value.Data != null -> {

            LaunchedEffect(Unit) {
                orderViewModel.getSpecificOrderViewModel(orderId)
            }
            orderViewModel.resetUpdateOrderState()
            Log.d("@order_details", "OrderDetailScreen: ${getOutOfDeliveryOrderState.value.Data}")
            Toast.makeText(context, getOutOfDeliveryOrderState.value.Data!!.body()?.message, Toast.LENGTH_SHORT
            ).show()
        }

        getOutOfDeliveryOrderState.value.error != null -> {
            Toast.makeText(context, "${getOutOfDeliveryOrderState.value.error}", Toast.LENGTH_SHORT)
                .show()
        }
    }
    when {
        getDeliveredOrderState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        getDeliveredOrderState.value.Data != null -> {

            LaunchedEffect(Unit) {
                orderViewModel.getSpecificOrderViewModel(orderId)
            }
            orderViewModel.resetUpdateOrderState()
            Toast.makeText(context, getDeliveredOrderState.value.Data!!.body()?.message, Toast.LENGTH_SHORT
            ).show()
        }

        getDeliveredOrderState.value.error != null -> {
            Log.d("@order_details", "OrderDetailScreen: ${getDeliveredOrderState.value.error}")
            Toast.makeText(context, "${getDeliveredOrderState.value.error}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    when {
        getCancelledOrderState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        getCancelledOrderState.value.Data != null -> {

            LaunchedEffect(Unit) {
                orderViewModel.getSpecificOrderViewModel(orderId)
            }
            orderViewModel.resetUpdateOrderState()
            Log.d("@order_details", "OrderDetailScreen: ${getCancelledOrderState.value.Data}")
            Toast.makeText(
                context,
                getCancelledOrderState.value.Data!!.body()?.message,
                Toast.LENGTH_SHORT
            ).show()
        }

        getCancelledOrderState.value.error != null -> {
            Log.d("@order_details", "OrderDetailScreen: ${getCancelledOrderState.value.error}")
            Toast.makeText(context, "${getCancelledOrderState.value.error}", Toast.LENGTH_SHORT)
                .show()
        }
    }
      //    This State Use Multiple State  .getSpecificOrderState Contain 1.getSpecificProductState ,
      //     2. userStockState

    when {
        getSpecificOrderState.value.loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
        }

        getSpecificOrderState.value.Error != null -> {
            Toast.makeText(context, getSpecificOrderState.value.Error, Toast.LENGTH_SHORT).show()

        }

        getSpecificOrderState.value.Data != null -> {
            val orderData = getSpecificOrderState.value.Data!!.body()!![0]
            LaunchedEffect(Unit) {
                productViewModel.getSpecificProductID(orderData.order_id)
            }
            val getSpecificProductState = productViewModel.getSpecificProduct.collectAsState()

            val updateProductResponseData =
                productViewModel.updateProductResponseData.collectAsState()
            var productStock = 0

            when {
                getSpecificProductState.value.loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Loading...") }
                }
                getSpecificProductState.value.data != null -> {

                    productStock = getSpecificProductState.value.data!!.body()!![0].product_stock
                    productViewModel.resetProductAddScreenState()
                    Log.d("@stock", "OrderDetailScreen data: ${getSpecificProductState.value.data}")
                }

                getSpecificProductState.value.error != null -> {
                    Toast.makeText(context, "${getSpecificProductState.value.error}", Toast.LENGTH_SHORT
                    ).show() }
            }

                  // Detail UI Handle

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(softWhiteColor),
                contentAlignment = Alignment.TopCenter
            ) {

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .background(Color.White)
                ) {
                    Spacer(Modifier.height(8.dp))
                    TopAppBar(headerName = "Order Details", isCloseIcon = true) {
                        navController.navigateUp()
                    }
                    Spacer(Modifier.height(8.dp))
                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                    Spacer(Modifier.height(8.dp))

                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollSate)
                            .padding(8.dp)
                            .background(softWhiteColor )
                    ) {

                        ProductDetailCard(orderData)

                        Spacer(Modifier.height(8.dp))
                        ShippingDetailCard(orderData)

                        Spacer(Modifier.height(8.dp))
                        PriceDetailCard(orderData)
                        Spacer(Modifier.height(8.dp))
                        OrderUpdateCardButton(orderData,
                            approveOnClick = {
                                orderViewModel.orderApproveViewModel(
                                    orderId = orderData.order_id,
                                    orderApprove = 1,
                                    orderStatusStep = (orderData.order_status.toInt() + 1).toString()
                                )
                                //manage stock of product
                                //stock get of order product and then minus in stock of product with order Qty
                                productViewModel.updateStockProductViewModel(
                                    productId = orderData.product_id,
                                    productStock = productStock - orderData.product_quantity
                                )
                            },
                            disApproveOnClick = {
                                orderViewModel.orderApproveViewModel(
                                    orderId = orderData.order_id,
                                    orderApprove = 1,
                                    orderStatusStep = (orderData.order_status.toInt() - 1).toString()
                                )
                            },
                            shippedOnClick = {
                                orderViewModel.updateShippedDateOrder(
                                    orderId = orderData.order_id,
                                    shippedDate = java.util.Date().toString(),
                                    orderStatusStep = (orderData.order_status.toInt() + 1).toString()
                                )
                            },
                            outOfDeliveryOnClick = {
                                orderViewModel.updateOutOfDeliveryDateOrder(
                                    orderId = orderData.order_id,
                                    outOfDeliveryDate = java.util.Date().toString(),
                                    orderStatusStep = (orderData.order_status.toInt() + 1).toString()
                                )
                            },
                            deliveredOnClick = {
                                orderViewModel.updateDeliveredDateOrder(
                                    orderId = orderData.order_id,
                                    deliveredDate = java.util.Date().toString(),
                                    orderStatusStep = (orderData.order_status.toInt() + 1).toString()
                                )

                                //manage the user stock
                                stockViewModel.addOrderInUserStock(
                                    userId = orderData.user_id,
                                    orderId = orderData.order_id,
                                    productId = orderData.product_id,
                                    productName = orderData.product_name,
                                    userName = orderData.user_name,
                                    certified = true,
                                    productStock = orderData.product_quantity,
                                    productPrice = orderData.product_price,
                                    productCategory = orderData.product_category
                                )
                                if (userStockState.value.data != null) {

                                    stockViewModel.resetAddStockUserState()
                                    Log.d("find","Data  : ${userStockState.value.data }")
                                    Toast.makeText(context, userStockState.value.data!!.body()?.message, Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Log.d("find","Error  : ${userStockState.value.error }")
                                    Toast.makeText(context, userStockState.value.error.toString(), Toast.LENGTH_SHORT).show()
                                }
                                //add in sell history which product was sell and how much
                                sellHistoryViewModel.addInSellHistory(
                                    userId = orderData.user_id,
                                    productId = orderData.product_id,
                                    quantity = orderData.product_quantity.toString(),
                                    remainingStock = (productStock - orderData.product_quantity).toString(),
                                    dateOfSell = java.util.Date().toString(),
                                    totalAmount = orderData.totalPrice.toString(),
                                    productPrice = orderData.product_price.toString(),
                                    productName = orderData.product_name,
                                    productCategory = orderData.product_category,
                                    userName = orderData.user_name
                                )
                                if (addSellHistoryState.value.data != null) {
                                    Log.d("find","Data- addSellHistoryState  : ${addSellHistoryState.value.data }")

                                    sellHistoryViewModel.resetAddInSellHistoryState()
                                    Toast.makeText(
                                        context,
                                        addSellHistoryState.value.data!!.body()?.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Log.d("find","Error-  addSellHistoryState : ${addSellHistoryState.value.error }")

                                    Toast.makeText(context,
                                        addSellHistoryState.value.error.toString(), Toast.LENGTH_SHORT).show()
                                }

                            },
                            cancelOnClick = {
                                orderViewModel.updateCancelledStatusOrderViewModel(
                                    orderId = orderData.order_id,
                                    cancelStatus = "Cancelled",
                                    orderStatus = (orderData.order_status.toInt() + 1).toString()
                                )
                            }
                        )
                    }
                }
            }

        }
    }





}








@Composable
fun OrderUpdateCardButton(
    orderData: getAllOrderResponseItem,
    approveOnClick: () -> Unit = {},
    disApproveOnClick: () -> Unit = {},
    shippedOnClick: () -> Unit = {},
    outOfDeliveryOnClick: () -> Unit = {},
    deliveredOnClick: () -> Unit = {},
    cancelOnClick: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(softWhiteColor),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(softWhiteColor)
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonView(
                    text = "Approved",
                    color = GreenColor,
                    enabled = orderData.isApproved == 0
                ) {
                    approveOnClick()
                }
                ButtonView(
                    text = "DisApproved",
                    color = Color.Red,
                    enabled = orderData.isApproved == 1 && orderData.shipped_date == "null"
                ) {
                    disApproveOnClick()
                }
            }
            Spacer(Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonView(
                    text = "Shipped",
                    color = GreenColor,
                    enabled = orderData.shipped_date == "null" && orderData.out_of_delivery_date == "null" && orderData.isApproved == 1
                ) {
                    shippedOnClick()
                }
                ButtonView(
                    text = "Out of Delivery",
                    color = GreenColor,
                    enabled = orderData.shipped_date != "null" && orderData.delivered_date == "null" && orderData.out_of_delivery_date == "null"
                ) {
                    outOfDeliveryOnClick()
                }
            }
            Spacer(Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonView(
                    text = "Delivered",
                    color = GreenColor,
                    enabled = orderData.out_of_delivery_date != "null" && orderData.delivered_date == "null"
                ) {
                    deliveredOnClick()
                }
                ButtonView(
                    text = "Cancel Order",
                    color = Color.Red,
                    enabled = orderData.delivered_date == "null"
                ) {
                    cancelOnClick()
                }
            }
        }
    }

}

@Composable
fun PriceTextView(
    text: String,
    price: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text, style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto_light)),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )
        )
        Text(
            text = stringResource(R.string.rs) + " " + price, style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto_light)),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )
        )
    }
}

@Composable
fun PriceDetailCard(orderData: getAllOrderResponseItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(softWhiteColor),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(softWhiteColor)
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Price Details", style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(Modifier.height(8.dp))
            PriceTextView(
                text = "Selling Price",
                price = orderData.product_price.toString()
            )
            Spacer(Modifier.height(8.dp))
            PriceTextView(
                text = "Subtotal Price",
                price = orderData.subtotal_price.toString()
            )
            Spacer(Modifier.height(8.dp))
            PriceTextView(
                text = "Extra Discount",
                price = orderData.discount_price
            )
            Spacer(Modifier.height(8.dp))
            PriceTextView(
                text = "Delivery Charge",
                price = orderData.delivery_charge.toString()
            )
            Spacer(Modifier.height(8.dp))
            PriceTextView(
                text = "Tax Charge",
                price = orderData.tax_charge.toString()
            )
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(Modifier.height(8.dp))

            PriceTextView(
                text = "Total Price",
                price = orderData.totalPrice.toString()
            )
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Cash on Delivery : " +  "Rs${orderData.totalPrice}",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )

        }
    }
}


@Composable
fun ProductDetailCard(orderData: getAllOrderResponseItem) {
    val safeSubstring: (String?) -> String = { str ->
        if (str != null && str.length >= 10) str.substring(0, 10) else str ?: ""
    }
    val validCurrentStep = orderData.order_status.toInt().coerceIn(-1, 5)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(softWhiteColor),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(softWhiteColor)
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Order ID - ${orderData.order_id}", style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = orderData.product_name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }, style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Category - ${
                            orderData.product_category.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.ROOT
                                ) else it.toString()
                            }
                        }",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Qty - ${orderData.product_quantity}", style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text =  "Rs ${orderData.subtotal_price}",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        )
                    )

                }
                ImageComponent(
                    imageId = orderData.product_image_id,
                    imageSize = 100.dp,
                    padding = 8.dp,
                    shape = RectangleShape
                )
            }
            Spacer(Modifier.height(16.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(Modifier.height(16.dp))

            VerticalStepper(
                style = tabVerticalWithLabel(
                    totalSteps = 5,
                    currentStep = validCurrentStep,
                    trailingLabels = listOf(
                        { Text("Pending  ${safeSubstring(orderData.order_date)}") },
                        { Text("Ordered Approved ${safeSubstring(orderData.order_date)}") },
                        { Text("Shipped  ${safeSubstring(orderData.shipped_date)}") },
                        { Text("Out of Delivery  ${safeSubstring(orderData.out_of_delivery_date)}") },
                        { Text("Delivered  ${safeSubstring(orderData.delivered_date)}") }
                    )
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ShippingDetailCard(orderData: getAllOrderResponseItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(softWhiteColor),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(softWhiteColor)
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Shipping Details", style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(Modifier.height(8.dp))

            Text(
                text = orderData.user_name, style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Address : ${orderData.user_address}", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Street : ${orderData.user_street}", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Mobile No : ${orderData.user_mobile}", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "City : ${orderData.user_city}", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "State : ${orderData.user_state}", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Pincode : ${orderData.user_pinCode}", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(4.dp))
        }
    }
}
