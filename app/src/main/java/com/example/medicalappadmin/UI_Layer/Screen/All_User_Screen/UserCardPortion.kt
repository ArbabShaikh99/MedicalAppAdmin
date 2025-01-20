package com.example.medicalappadmin.UI_Layer.Screen.All_User_Screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicalappadmin.Data_Layer.Response.User.GetAllUserResponseItem
import com.example.medicalappadmin.ui.theme.GreenColor
import com.example.medicalappadmin.ui.theme.softWhiteColor


@Composable
fun UserCardPortion(
    userResponseItem: GetAllUserResponseItem,
    approveOnClick: () -> Unit,
    disApproveOnClick: () -> Unit,
    blockOnClick: () -> Unit,
    unblockOnClick: () -> Unit,
    deleteOnClick: () -> Unit

) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(softWhiteColor)
            .padding(4.dp)
            .height(500.dp), shape = RoundedCornerShape(16.dp), border = BorderStroke(2.dp,
            color = Color.Black
        ), elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(softWhiteColor)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            AsyncImageComponent(
//                imageId = userResponseItem.user_image_id,
//                imageSize = 90.dp,
//                shape = CircleShape,
//                modifier = Modifier
//                    .wrapContentWidth()
//                    .wrapContentHeight()
//            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text ="User Id:${userResponseItem.user_id}" ,
                fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)

            Text(text ="User Name: ${userResponseItem.name}" ,
                fontSize = 17.sp, fontWeight = FontWeight.Normal, color = Color.Black)

            Text(text ="Email: ${userResponseItem.email}" ,
                fontSize = 17.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Text(text = "Password: ${userResponseItem.password}" ,
                fontSize = 17.sp, fontWeight = FontWeight.Medium, color = Color.Black)

            Text(text = "Phone Number: ${userResponseItem.phone_number}" ,
                fontSize = 17.sp, fontWeight = FontWeight.Medium, color = Color.Black)

            Text(text = "PinCode: ${userResponseItem.pinCode}" ,
                fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Black)

            Text(text = "Address: ${userResponseItem.address}" ,
                fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Black)

            Text(text = "is Approved: ${userResponseItem.isApproved}" ,
                fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Text(text = "is Blocked: ${userResponseItem.block}" ,
                fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)

            Text(text = "Date of creation: ${userResponseItem.date_of_account_creation}",
                fontSize = 16.sp, fontWeight = FontWeight.Normal , color = Color.Black)


           // TextView(text = "UserImageId: ${userResponseItem.user_image_id}")

            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonView(
                    text = "Approved",
                    color = Color.White,
                    enabled = userResponseItem.isApproved == 0
                ) {
                    approveOnClick()
                }
                ButtonView(
                    text = "DisApproved",
                    color = Color.Red,
                    enabled = userResponseItem.isApproved == 1
                ) {
                    disApproveOnClick()
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonView(
                    text = "Block",
                    color = Color.White,
                    enabled = userResponseItem.block == 0
                ) {
                    blockOnClick()
                }
                ButtonView(
                    text = "UnBlock",
                    color = Color.Red,
                    enabled = userResponseItem.block == 1
                ) {
                    unblockOnClick()
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonView(
                    text = "Delete",
                    color = Color.White,
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    deleteOnClick()
                }
            }


        }

    }
}








@Composable
fun TextView(
    text: String,
    color: Color = Color.Black,
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text, style = TextStyle(
            color = color,
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
// fontFamily = FontFamily(Font(R.font.roboto_regular))
        )
    )
}

@Composable
fun ButtonView(
    text: String,
    color: Color,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.Black
        ),
        enabled = enabled,
        modifier = modifier

    ) {
        Text(text = text)
    }

}

















