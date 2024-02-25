package com.example.fivechessgame.statistic

object StatUtils {
    fun uploadShowStat() {
        uploadEvent<DemoEvent> {
            demoTime = System.currentTimeMillis()
            testAction = "test"
        }
    }
}