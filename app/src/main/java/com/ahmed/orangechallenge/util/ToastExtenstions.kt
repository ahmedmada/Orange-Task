package com.ahmed.orangechallenge.util

import android.content.Context
import android.widget.Toast

fun Context.showToast(
    message: String?,
    toastType: ToastType = ToastType.ERROR,
    withIcon: Boolean = true
) {
    if (message.isNullOrEmpty()) return
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}

enum class ToastType {
    SUCCESS, ERROR, WARNING, INFO
}
