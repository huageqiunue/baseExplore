package com.junhua.myapplication.statistic

object StatUtils {
    fun uploadShowStat() {
        uploadEvent<DemoEvent> {
            demoTime = System.currentTimeMillis()
            testAction = "test"
        }
    }
}