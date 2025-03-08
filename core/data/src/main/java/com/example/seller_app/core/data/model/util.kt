package com.example.seller_app.core.data.model

import android.content.Context
import android.net.Uri
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream


internal fun String.toMultipart(): RequestBody {
    return this.toRequestBody("text/plain".toMediaTypeOrNull())
}

internal fun Boolean.toMultipart(): RequestBody {
    return this.toString().toRequestBody("text/plain".toMediaTypeOrNull())
}

internal fun Int.toMultipart(): RequestBody {
    return this.toString().toRequestBody("text/plain".toMediaTypeOrNull())
}


fun File.toMultipart(): MultipartBody.Part {
    val requestBody = this.asRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("image", this.name, requestBody)
}



fun getFileFromUri(uri: Uri, context: Context): File {
    val file = File(context.filesDir, "image.jpg")
    val inputStream = context.contentResolver.openInputStream(uri)
    val outputStream = FileOutputStream(file)
    inputStream!!.copyTo(outputStream)

    return file
}