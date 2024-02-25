package com.example.fivechessgame.statistic

class DemoEvent : BaseEventStat() {
    companion object {
        const val EVENT_NAME = "demo"
        const val EVENT_CLASSIFY = "demo_classify"
    }

    override val eventName: String
        get() = EVENT_NAME
    override val eventClassify: String
        get() = EVENT_CLASSIFY

    var demoTime by long()

    var testAction by string()

}
