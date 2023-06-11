package com.junhua.common

import java.io.Closeable


fun Closeable?.closeQuietly() {
    try {
        this?.close()
    } catch (e: Throwable) {
        // ignore
    }
}