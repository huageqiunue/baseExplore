package com.example.storage

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.openSafForCallback(safParams: SafParams, safCallback: SafCallback) {
    SafFragment.getSafFragment(this).openSaf(safParams, object : SafCallback() {
        override fun succeed(arrayList: List<Uri?>) {
            safCallback.onSucceed?.invoke(arrayList)
        }

        override fun failed(errorString: String) {
            safCallback.onFailed?.invoke(errorString)
        }
    })
}

fun FragmentActivity.openSafForCallback(
    safParams: SafParams,
    safCallbackExtension: SafCallback.() -> Unit
) {
    val safCallback = SafCallback().apply(safCallbackExtension)
    SafFragment.getSafFragment(this).openSaf(safParams, object : SafCallback() {
        override fun succeed(arrayList: List<Uri?>) {
            safCallback.onSucceed?.invoke(arrayList)
        }

        override fun failed(errorString: String) {
            safCallback.onFailed?.invoke(errorString)
        }
    })
}

fun Fragment.openSafForCallback(safParams: SafParams, safCallback: SafCallback) {
    SafFragment.getSafFragment(this).openSaf(safParams, safCallback)
}