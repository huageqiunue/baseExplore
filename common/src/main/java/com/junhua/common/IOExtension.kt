package com.junhua.common

import java.io.Closeable


/**
 * 关闭流
 */
fun Closeable?.closeQuietly() {
    try {
        this?.close()
    } catch (e: Throwable) {
        // ignore
    }
}