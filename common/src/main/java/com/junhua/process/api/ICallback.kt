package com.junhua.process.api

/**
 * callback回调
 */
interface ICallback {
    fun onInit()
    fun onSerialize()
    fun onProcess()
    fun onNotify()
    fun onFinish()
}