package com.example.medicalappadmin.UI_Layer.Screen.Product_Screen

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.medicalappadmin.Data_Layer.getFileFromUri
import com.example.medicalappadmin.Data_Layer.isFloat
import com.example.medicalappadmin.Data_Layer.isInteger
import com.example.medicalappadmin.R
import com.example.medicalappadmin.UI_Layer.ScreenState.AddProductScreenState
import com.example.medicalappadmin.ui.theme.lightBlackColor
import com.example.medicalappadmin.ViewModel.ProductViewModel
import com.example.medicalappadmin.ui.theme.softWhiteColor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

@Composable
fun AddProductScreen(viewModel: ProductViewModel= hiltViewModel()) {

    val context = LocalContext.current
    val productAddResponseState = viewModel.addProductState.collectAsState()
    val AddProductScreen = viewModel.addProductScreenData.collectAsState()

    //val focusRequester = remember { FocusRequester() }
    when {
        productAddResponseState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }

        productAddResponseState.value.Data != null -> {
            Log.d("error", "productAddResponseState: ${productAddResponseState.value.Data}")

//            Toast.makeText(context, "${productAddResponseState.value.Data?.body()?.message}",
//                Toast.LENGTH_SHORT
//            ).show()

            LaunchedEffect(Unit) {
                if (productAddResponseState.value.Data!!.body()?.status == 200) {
                    viewModel.resetProductAddScreenState()
                    Toast.makeText(
                        context,
                        productAddResponseState.value.Data!!.body()?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    Toast.makeText(context, "Product Added Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
                }
            }
        }


        productAddResponseState.value.Error != null -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = productAddResponseState.value.Error.toString())
            }
        }

    }
    val scrollState = rememberScrollState()
    val focusRequester = FocusRequester()

    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
    }
    Box(modifier = Modifier.fillMaxSize().background(softWhiteColor)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Add Product",
                modifier = Modifier.padding(16.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(25.dp))

            Column(
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)

            ) {
                TextField(
                    value = AddProductScreen.value.productName.value,
                    onValueChange = { AddProductScreen.value.productName.value = it },
                    label = { Text("Product Name",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth()
                        .focusRequester(focusRequester),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor),
                    maxLines = 2
                )
                TextField(
                    value = AddProductScreen.value.productPrice.value,
                    onValueChange = { AddProductScreen.value.productPrice.value = it },
                    label = { Text("Product Price",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor),
                    maxLines = 1
                )
                TextField(
                    value = AddProductScreen.value.productCategory.value,
                    onValueChange = { AddProductScreen.value.productCategory.value = it },
                    label = { Text("Product Category",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor),
                    maxLines = 1
                )
                TextField(
                    value = AddProductScreen.value.productStock.value,
                    onValueChange = { AddProductScreen.value.productStock.value = it },
                    label = { Text("Product Stock",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor),
                    maxLines = 1
                )
                TextField(
                    value = AddProductScreen.value.productPower.value,
                    onValueChange = { AddProductScreen.value.productPower.value = it },
                    label = { Text("Product Power",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor),
                    maxLines = 1
                )

              //  Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = AddProductScreen.value.productRating.value,
                    onValueChange = { AddProductScreen.value.productRating.value = it },
                    label = { Text("Product Rating",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor),
                    maxLines = 1
                )

                TextField(
                    value = AddProductScreen.value.productDescription.value,
                    onValueChange = { AddProductScreen.value.productDescription.value = it },
                    label = { Text("Product Description",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor),
                    maxLines = 8
                )
                TextField(
                    value = AddProductScreen.value.productExpiryDate.value,
                    onValueChange = { AddProductScreen.value.productExpiryDate.value = it },
                    label = { Text("Product Expiry Date",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor),
                    maxLines = 1
                )







                SelectItemImageSection(AddProductScreen)
                Spacer(modifier = Modifier.height(8.dp))


                Button(
                    onClick = {

                        if (AddProductScreen.value.pic.value != null) {

                            val imageFile =
                                getFileFromUri(context, AddProductScreen.value.pic.value!!)

                            if (imageFile != null && imageFile.exists()) {
                                val requestFile =
                                    imageFile.asRequestBody("image/jpg".toMediaTypeOrNull())
                                val body =
                                    MultipartBody.Part.createFormData(
                                        "pic", imageFile.name, requestFile)


//                                if (AddProductScreen.value.productName.value.isNotEmpty() && AddProductScreen.value.productCategory.value.isNotEmpty() &&
//                                    AddProductScreen.value.productPrice.value.isNotEmpty() && AddProductScreen.value.productDescription.value.isNotEmpty() &&
//                                    AddProductScreen.value.productPower.value.isNotEmpty() && AddProductScreen.value.productRating.value.isNotEmpty() &&
//                                    AddProductScreen.value.productStock.value.isNotEmpty() && AddProductScreen.value.productExpiryDate.value.isNotEmpty()
//                                )
                                    if (isInteger(AddProductScreen.value.productPrice.value) && isInteger(
                                            AddProductScreen.value.productStock.value
                                        ) && isFloat(AddProductScreen.value.productRating.value))
//
                                {
//                                         Toast.makeText(context, "Please enter valid data", Toast.LENGTH_SHORT).show()
                                    viewModel.AddProduct(
                                        productName = AddProductScreen.value.productName.value,
                                        productCategory = AddProductScreen.value.productCategory.value,
                                        productPrice = AddProductScreen.value.productPrice.value.toInt(),
                                        productDescription = AddProductScreen.value.productDescription.value,
                                        productImageFile = body,
                                        productPower = AddProductScreen.value.productPower.value,
                                        productRating = AddProductScreen.value.productRating.value.toFloat(),
                                        productStock = AddProductScreen.value.productStock.value.toInt(),
                                        productExpiryDate = AddProductScreen.value.productExpiryDate.value
                                    )
                                } else {

                                    Toast.makeText(
                                        context,
                                        "Please enter All Fields",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Log.e(
                                    "UploadError",
                                    "Image file not found or could not be accessed."
                                )
                            }
                        } else {
                            Toast.makeText(context, "Please select image", Toast.LENGTH_SHORT)
                                .show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(
                        text = "Add Product",
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))


            }

        }
//                item {
//                    Button(
//                        onClick = {
//                            if (AddProductScreen.productName.value.isNotEmpty() &&
//                                AddProductScreen.productCategory.value.isNotEmpty() &&
//                                AddProductScreen.productPrice.value.isNotEmpty() &&
//                                AddProductScreen.productStock.value.isNotEmpty() &&
//                                AddProductScreen.productPower.value.isNotEmpty() &&
//                                AddProductScreen.productRating.value.isNotEmpty() &&
//                                AddProductScreen.productDescription.value.isNotEmpty() &&
//                                AddProductScreen.pic.value.isNotEmpty() &&
//                                AddProductScreen.productExpiryDate.value.isNotEmpty()
//                            ) {
//                                viewModel.AddProduct(
//                                    productName = AddProductScreen.productName.value,
//                                    productCategory = AddProductScreen.productCategory.value,
//                                    ProductPrice = AddProductScreen.productPrice.value.toInt(),
//                                    ProductStock = AddProductScreen.productStock.value.toInt(),
//                                    productPower = AddProductScreen.productPower.value,
//                                    productRating = AddProductScreen.productRating.value.toFloat(),
//                                    productDescription = AddProductScreen.productDescription.value,
//                                    pic = AddProductScreen.pic.value,
//                                    productExpiryDate = AddProductScreen.productExpiryDate.value
//                                )
//                            } else {
//                                Toast.makeText(context, "Select All Field", Toast.LENGTH_SHORT).show()
//                            }
//                        },
//                        colors = ButtonDefaults.buttonColors(Color.Black),
//                        border = BorderStroke(1.dp, Color.Black)
//                    ) {
//                        Text(
//                            text = "Add Product",
//                            color = Color.White
//                        )
//                    }
//                }
//            }
//        }
        }
    }




@Composable
fun SelectItemImageSection(AddProductScreen: State<AddProductScreenState>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val launcher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(),
                onResult = {
                    AddProductScreen.value.pic.value = it
                })
        Row(
            modifier = Modifier
                .shadow(1.dp, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Select Medicine Image", style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                )
            )

            IconButton(onClick = {
                launcher.launch("image/*")
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_to_photos_24),
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        }
        AddProductScreen.value.pic.value?.let {
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                painter = rememberAsyncImagePainter(AddProductScreen.value.pic.value),
                contentDescription = "",
                modifier = Modifier
                    .height(110.dp)
                    .width(175.dp)
            )
        }
    }
}