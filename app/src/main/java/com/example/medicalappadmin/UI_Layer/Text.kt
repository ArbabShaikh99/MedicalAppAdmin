import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Check(){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "PharmaPe",
               // style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Login to Your Account",
               // style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Button(
                onClick = { /* Handle Sign In */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign in")
            }
            TextButton(
                onClick = { /* Handle Forgot Password */ },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Forgot password?")
            }
            TextButton(
                onClick = { /* Handle Sign Up Navigation */ },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Don't have an account? Click here")
            }
        }
    }












//
//package com.example.medicalappadmin.UI_Layer.Screen.Product_Screen
//
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import coil.compose.rememberAsyncImagePainter
//
//// OR
////import androidx.compose.material.TextFieldDefaults
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.State
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.focus.FocusRequester
//import androidx.compose.ui.focus.focusRequester
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.medicalappadmin.Data_Layer.getFileFromUri
//import com.example.medicalappadmin.Data_Layer.isFloat
//import com.example.medicalappadmin.Data_Layer.isInteger
//import com.example.medicalappadmin.R
//import com.example.medicalappadmin.UI_Layer.ScreenState.AddProductScreenState
//import com.example.medicalappadmin.ViewModel.ProductViewModel
//import com.example.medicalappadmin.ui.theme.softWhiteColor
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.MultipartBody
//import okhttp3.RequestBody.Companion.asRequestBody
//
//@Composable
//fun AddProductScreen(viewModel: ProductViewModel= hiltViewModel()) {
//
//    val context = LocalContext.current
//    val productAddResponseState = viewModel.addProductState.collectAsState()
//    val AddProductScreen by viewModel.addProductScreenData.collectAsState()
//    //val focusRequester = remember { FocusRequester() }
//    when {
//        productAddResponseState.value.loading -> {
//            Box(modifier = Modifier.fillMaxSize()) {
//                CircularProgressIndicator()
//            }
//        }
//
//        productAddResponseState.value.Data != null -> {
//            Toast.makeText(
//                context, "${productAddResponseState.value.Data?.body()?.message}",
//                Toast.LENGTH_SHORT
//            ).show()
//
//            LaunchedEffect(Unit) {
//                if (productAddResponseState.value.Data!!.body()?.status == 200) {
//                    viewModel.resetProductAddScreenState()
//                    Toast.makeText(
//                        context,
//                        productAddResponseState.value.Data!!.body()?.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    Toast.makeText(context, "Product Added Successfully", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//
//
//        productAddResponseState.value.Error != null -> {
//            Box(modifier = Modifier.fillMaxSize()) {
//                Text(text = productAddResponseState.value.Error.toString())
//            }
//        }
//
//    }
//    val scrollState = rememberScrollState()
//    val focusRequester = FocusRequester()
//
//    LaunchedEffect(key1 = true) {
//        focusRequester.requestFocus()
//    }
//    Box(modifier = Modifier.fillMaxSize().background(softWhiteColor)) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top
//        ) {
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Text(
//                text = "Add Product",
//                modifier = Modifier.padding(16.dp),
//                fontSize = 28.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//
//            Spacer(modifier = Modifier.height(25.dp))
//
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                contentPadding = PaddingValues(horizontal = 16.dp),
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                item {
//                    TextField(
//                        // modifier = Modifier.focusRequester(focusRequester),
//                        value = AddProductScreen.productName.value,
//                        onValueChange = { AddProductScreen.productName.value = it },
//                        label = { Text("Product name") },
//                        modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
//                        colors = TextFieldDefaults.colors(unfocusedContainerColor = softWhiteColor)
//                    )
//                }
//
//                item {
//                    TextField(
//                        value = AddProductScreen.productPrice.value,
//                        onValueChange = { AddProductScreen.productPrice.value = it },
//                        label = { Text("Product Price") },
//                        modifier = Modifier.fillMaxWidth(),
//                        colors = TextFieldDefaults.colors(unfocusedContainerColor = softWhiteColor)
//                    )
//                }
//
//                item {
//                    TextField(
//                        value = AddProductScreen.productCategory.value,
//                        onValueChange = { AddProductScreen.productCategory.value = it },
//                        label = { Text("Product Category") },
//                        modifier = Modifier.fillMaxWidth(),
//                        colors = TextFieldDefaults.colors(unfocusedContainerColor = softWhiteColor)
//                    )
//                }
//
//                item {
//                    TextField(
//                        value = AddProductScreen.productStock.value,
//                        onValueChange = { AddProductScreen.productStock.value = it },
//                        label = { Text("Product Stock") },
//                        modifier = Modifier.fillMaxWidth(),
//                        colors = TextFieldDefaults.colors(unfocusedContainerColor = softWhiteColor)
//                    )
//                }
//
//                item {
//                    TextField(
//                        value = AddProductScreen.productPower.value,
//                        onValueChange = { AddProductScreen.productPower.value = it },
//                        label = { Text("Product Power") },
//                        modifier = Modifier.fillMaxWidth(),
//                        colors = TextFieldDefaults.colors(unfocusedContainerColor = softWhiteColor)
//                    )
//                }
//
//                item {
//                    TextField(
//                        value = AddProductScreen.productRating.value,
//                        onValueChange = { AddProductScreen.productRating.value = it },
//                        label = { Text("Product Rating") },
//                        modifier = Modifier.fillMaxWidth(),
//                        colors = TextFieldDefaults.colors(unfocusedContainerColor = softWhiteColor)
//                    )
//                }
//
//                item {
//                    TextField(
//                        value = AddProductScreen.productDescription.value,
//                        onValueChange = { AddProductScreen.productDescription.value = it },
//                        label = { Text("Product Description") },
//                        modifier = Modifier.fillMaxWidth(),
//                        colors = TextFieldDefaults.colors(unfocusedContainerColor = softWhiteColor)
//                    )
//                }
//
//                item {
//                    TextField(
//                        value = AddProductScreen.productExpiryDate.value,
//                        onValueChange = { AddProductScreen.productExpiryDate.value = it },
//                        label = { Text("Expiry Date") },
//                        modifier = Modifier.fillMaxWidth(),
//                        colors = TextFieldDefaults.colors(unfocusedContainerColor = softWhiteColor)
//                    )
//                }
//
//                item {
//                    SelectItemImageSection(AddProductScreen)
////                    TextField(
////                        value = AddProductScreen.pic.value,
////                        onValueChange = { AddProductScreen.pic.value = it },
////                        label = { Text("Product pic" ,modifier = Modifier.background(softWhiteColor)) },
////                        modifier = Modifier.fillMaxWidth(),
////                        colors = TextFieldDefaults.colors(unfocusedContainerColor = softWhiteColor)
////                    )
//                }
//                item {
//                    Button(
//                        onClick = {
//
//                            if (AddProductScreen.pic.value != null) {
//
//                                val imageFile =
//                                    getFileFromUri(context, AddProductScreen.pic.value!!)
//
//                                if (imageFile != null && imageFile.exists()) {
//                                    val requestFile =
//                                        imageFile.asRequestBody("image/jpg".toMediaTypeOrNull())
//                                    val body =
//                                        MultipartBody.Part.createFormData(
//                                            "pic",
//                                            imageFile.name,
//                                            requestFile
//                                        )
//
//                                    if (isInteger(AddProductScreen.productPrice.value) && isInteger(
//                                            AddProductScreen.productStock.value
//                                        ) && isFloat(AddProductScreen.productRating.value)
//                                    ) {
//
//                                        viewModel.AddProduct(
//                                            productName = AddProductScreen.productName.value,
//                                            productCategory = AddProductScreen.productCategory.value,
//                                            productPrice = AddProductScreen.productPrice.value.toInt(),
//                                            productDescription = AddProductScreen.productDescription.value,
//                                            productImageFile = body,
//                                            productPower = AddProductScreen.productPower.value,
//                                            productRating = AddProductScreen.productRating.value.toFloat(),
//                                            productStock = AddProductScreen.productStock.value.toInt(),
//                                            productExpiryDate = AddProductScreen.productExpiryDate.value
//                                        )
//                                    } else{
//
//                                        Toast.makeText(
//                                            context,
//                                            "Please enter valid data",
//                                            Toast.LENGTH_SHORT
//                                        )
//                                            .show()
//                                    }
//                                } else {
//                                    Log.e(
//                                        "UploadError",
//                                        "Image file not found or could not be accessed."
//                                    )
//                                }
//                            } else {
//                                Toast.makeText(context, "Please select image", Toast.LENGTH_SHORT)
//                                    .show()
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
//                    Spacer(modifier = Modifier.height(40.dp))
//
//                }
//
//            }
//
//
////                item {
////                    Button(
////                        onClick = {
////                            if (AddProductScreen.productName.value.isNotEmpty() &&
////                                AddProductScreen.productCategory.value.isNotEmpty() &&
////                                AddProductScreen.productPrice.value.isNotEmpty() &&
////                                AddProductScreen.productStock.value.isNotEmpty() &&
////                                AddProductScreen.productPower.value.isNotEmpty() &&
////                                AddProductScreen.productRating.value.isNotEmpty() &&
////                                AddProductScreen.productDescription.value.isNotEmpty() &&
////                                AddProductScreen.pic.value.isNotEmpty() &&
////                                AddProductScreen.productExpiryDate.value.isNotEmpty()
////                            ) {
////                                viewModel.AddProduct(
////                                    productName = AddProductScreen.productName.value,
////                                    productCategory = AddProductScreen.productCategory.value,
////                                    ProductPrice = AddProductScreen.productPrice.value.toInt(),
////                                    ProductStock = AddProductScreen.productStock.value.toInt(),
////                                    productPower = AddProductScreen.productPower.value,
////                                    productRating = AddProductScreen.productRating.value.toFloat(),
////                                    productDescription = AddProductScreen.productDescription.value,
////                                    pic = AddProductScreen.pic.value,
////                                    productExpiryDate = AddProductScreen.productExpiryDate.value
////                                )
////                            } else {
////                                Toast.makeText(context, "Select All Field", Toast.LENGTH_SHORT).show()
////                            }
////                        },
////                        colors = ButtonDefaults.buttonColors(Color.Black),
////                        border = BorderStroke(1.dp, Color.Black)
////                    ) {
////                        Text(
////                            text = "Add Product",
////                            color = Color.White
////                        )
////                    }
////                }
////            }
////        }
//        }
//    }
//}
//
//
//
//@Composable
//fun SelectItemImageSection(productAddScreenState: AddProductScreenState) {
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.White),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//
//        val launcher =
//            rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(),
//                onResult = {
//                    productAddScreenState.pic.value = it
//                })
//        Row(
//            modifier = Modifier
//                .shadow(1.dp, shape = RoundedCornerShape(8.dp))
//                .fillMaxWidth()
//                .background(Color.White)
//                .padding(10.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "Select Medicine Image", style = TextStyle(
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.W400,
//                    fontFamily = FontFamily(Font(R.font.roboto_medium))
//                )
//            )
//
//            IconButton(onClick = {
//                launcher.launch("image/*")
//            }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.baseline_add_to_photos_24),
//                    contentDescription = "",
//                    tint = Color.Black
//                )
//            }
//        }
//        productAddScreenState.pic.value?.let {
//            Spacer(modifier = Modifier.height(5.dp))
//            Image(
//                painter = rememberAsyncImagePainter(productAddScreenState.pic.value),
//                contentDescription = "",
//                modifier = Modifier
//                    .height(110.dp)
//                    .width(175.dp)
//            )
//        }
//    }
//}