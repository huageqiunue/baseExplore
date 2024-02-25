package com.example.fivechessgame.statistic

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class BaseEventStat {
    private val tag = "BaseEventStat"

    // 所有上报参数得收集，这一块可以自己定义和修改
    private val paramsMap = hashMapOf<String, Any?>()

    // 埋点名字
    abstract val eventName: String

    // 埋点所属归类
    abstract val eventClassify: String

    /**
     * 触发上报当前的埋点
     */
    fun uploadEventStat() {
//        EventTrack.stat(eventTrackCategory, eventId, paramsMap)
    }

    /**
     * 定义每种类型的委托方法，控制它的get和set方法，如果有新增，可以在这里新增
     */
    protected fun int(): ReadWriteProperty<BaseEventStat, Int?> = InnerReadWriteProperty(0)

    protected fun string(): ReadWriteProperty<BaseEventStat, String?> = InnerReadWriteProperty("")

    protected fun long(): ReadWriteProperty<BaseEventStat, Long?> = InnerReadWriteProperty(0)
    protected fun boolean(): ReadWriteProperty<BaseEventStat, Boolean?> = InnerReadWriteProperty(false)

    protected fun float(): ReadWriteProperty<BaseEventStat, Float?> = InnerReadWriteProperty(0F)

    protected fun double(): ReadWriteProperty<BaseEventStat, Double?> = InnerReadWriteProperty(0.0)

    // 代理类属性的处理
    inner class InnerReadWriteProperty<T, V>(defaultValue: V) : ReadWriteProperty<T, V> {
        private var currentValue: V = defaultValue
        override fun getValue(thisRef: T, property: KProperty<*>): V {
            return currentValue
        }

        override fun setValue(thisRef: T, property: KProperty<*>, value: V) {
            currentValue = value
            paramsMap[property.name] = value
        }
    }

}
