package com.junhua.process

import com.junhua.process.api.INode
import com.junhua.process.node.*

/**
 * 请求管理类
 */
object RequestManager {

    val list = arrayListOf(
        InitNode(),
        SerializeNode(),
        ProcessNode(),
        NotifyNode(),
        FinishNode()
    )

    fun request(){

    }
}