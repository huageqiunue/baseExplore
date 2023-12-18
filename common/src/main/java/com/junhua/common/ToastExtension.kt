package com.junhua.common

import android.content.Context
import android.widget.Toast

fun Context.showToast(resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_LONG)
}

fun Context.showToast(str: String) {
    Toast.makeText(this, str, Toast.LENGTH_LONG)
}