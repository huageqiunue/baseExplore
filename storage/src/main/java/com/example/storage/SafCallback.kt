package com.example.storage

import android.net.Uri

open class SafCallback {
    var onSucceed: ((ArrayList<Uri>) -> Unit)? = null
    var onError: ((String) -> Unit)? = null


    fun onSucceed(onSucceed: ((ArrayList<Uri>) -> Unit)) {
        this.onSucceed = onSucceed
    }

    fun onError(onError: ((String) -> Unit)) {
        this.onError = onError
    }

    open fun succeed(arrayList: ArrayList<Uri>) {
        this.onSucceed?.invoke(arrayList)
    }

    open fun error(errorString: String) {
        this.onError?.invoke(errorString)
    }
}