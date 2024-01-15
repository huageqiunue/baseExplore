package com.junhua.myapplication.statistic

/**
 * @param block 初始化函数
 */
inline fun <reified T : BaseEventStat> uploadEvent(crossinline block: T.() -> Unit) {
    runCatching {
        // 实例化泛型T
        val clz = T::class.java
        val instance = clz.getDeclaredConstructor()
        instance.isAccessible = true
        val tInstance = instance.newInstance()
        // 触发对象初始化
        block.invoke(tInstance)
        // 触发上报
        tInstance.uploadEventStat()
    }.onFailure {
        // 抛出异常，或者使用兜底方案
        it.printStackTrace()
    }
}
