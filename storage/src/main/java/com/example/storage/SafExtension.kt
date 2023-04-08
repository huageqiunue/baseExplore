package com.example.storage

import android.net.Uri
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.openSafForCallback(safParams: SafParams, safCallback: SafCallback) {
    SafFragment.getSafFragment(this).openSaf(safParams, object : SafCallback() {
        override fun succeed(arrayList: ArrayList<Uri>) {
            safCallback.onSucceed?.invoke(arrayList)
        }

        override fun error(errorString: String) {
        }
    })
}

fun FragmentActivity.openSafForCallback(
    safParams: SafParams,
    safCallbackExtension: SafCallback.() -> Unit
) {
    val safCallback = SafCallback().apply(safCallbackExtension)
    SafFragment.getSafFragment(this).openSaf(safParams, object : SafCallback() {
        override fun succeed(arrayList: ArrayList<Uri>) {
            safCallback.onSucceed?.invoke(arrayList)
        }

        override fun error(errorString: String) {
            safCallback.onError?.invoke(errorString)
        }
    })
}

fun Fragment.openSafForCallback(safParams: SafParams, safCallback: SafCallback) {
    SafFragment.getSafFragment(this).openSaf(safParams, safCallback)
}