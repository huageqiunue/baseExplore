package com.example.storage

import android.net.Uri

open class SafCallback {
    var onSucceed: ((List<Uri?>) -> Unit)? = null
    var onFailed: ((String) -> Unit)? = null


    fun onSucceed(onSucceed: ((List<Uri?>) -> Unit)) {
        this.onSucceed = onSucceed
    }

    fun onFailed(onError: ((String) -> Unit)) {
        this.onFailed = onError
    }

    open fun succeed(arrayList: List<Uri?>) {
        this.onSucceed?.invoke(arrayList)
    }

    open fun failed(errorString: String) {
        this.onFailed?.invoke(errorString)
    }
}