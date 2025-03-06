package com.example.seller_app.core.data.model

import android.content.Context
import android.net.Uri
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream


internal fun String.toMultipart(): RequestBody {
    return RequestBody.create(MediaType.parse("text/plain"), this)
}

internal fun Boolean.toMultipart(): RequestBody {
    return RequestBody.create(MediaType.parse("text/plain"), this.toString())
}

internal fun Int.toMultipart(): RequestBody {
    return RequestBody.create(MediaType.parse("text/plain"), this.toString())
}


fun File.toMultipart(): MultipartBody.Part {
    val requestBody = RequestBody.create(MediaType.parse("image/*"), this)
    return MultipartBody.Part.createFormData("image", this.name, requestBody)
}



fun getFileFromUri(uri: Uri, context: Context): File {
    val file = File(context.filesDir, "image.jpg")
    val inputStream = context.contentResolver.openInputStream(uri)
    val outputStream = FileOutputStream(file)
    inputStream!!.copyTo(outputStream)

    return file
}