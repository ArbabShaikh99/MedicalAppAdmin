package com.example.medicalappadmin.Data_Layer

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream


const val BASE_URL ="https://arbab9999.pythonanywhere.com"
const val IMG_URL_ID = "https://arbab9999.pythonanywhere.com/getImg/"


fun getFileFromUri(context: Context, uri: Uri): File? {
    val contentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(uri) ?: return null
    val file = File(context.cacheDir, "temp_image.jpg") // Temporary file in cache

    inputStream.use { input ->
        FileOutputStream(file).use { output ->
            input.copyTo(output)
        }
    }
    return file
}

fun isInteger(input: String): Boolean {
    return try {
        input.toInt()
        true
    } catch (e: NumberFormatException) {
        false
    }
}
fun isFloat(input: String): Boolean {
    return try {
        input.toFloat()
        true
    } catch (e: NumberFormatException) {
        false
    }
}