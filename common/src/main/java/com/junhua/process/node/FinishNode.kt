package com.junhua.process.node

import com.junhua.process.api.ICallback
import com.junhua.process.api.INode

class FinishNode : INode {

    override fun process(callback: ICallback) {
        callback.onProcess()
        TODO("Not yet implemented")
    }
}