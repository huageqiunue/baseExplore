package com.example.fivechessgame.sp

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * SharedPreferences委托代理
 * @param context Context
 * @param spName SP存入的XML名字
 * @param defaultValue 默认值
 * @param key 存取数据时对应的key
 */
class SharedPreferencesDelegate<T>(
    private val context: Context,
    private val spName: String,
    private val defaultValue: T,
    private val key: String? = null,
) : ReadWriteProperty<Any?, T> {

    private val sp: SharedPreferences by lazy(LazyThreadSafetyMode.NONE) {
        context.getSharedPreferences(spName, Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val finalKey = key ?: property.name
        with(sp) {
            return when (defaultValue) {
                is Int -> getInt(finalKey, defaultValue)
                is Long -> getLong(finalKey, defaultValue)
                is Float -> getFloat(finalKey, defaultValue)
                is Boolean -> getBoolean(finalKey, defaultValue)
                is String -> getString(finalKey, defaultValue)
                else -> throw IllegalStateException("Unsupported type")
            } as T
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val finalKey = key ?: property.name
        with(sp.edit()) {
            when (value) {
                is Int -> putInt(finalKey, value)
                is Long -> putLong(finalKey, value)
                is Float -> putFloat(finalKey, value)
                is Boolean -> putBoolean(finalKey, value)
                is String -> putString(finalKey, value)
                else -> throw IllegalStateException("Unsupported type")
            }
            apply()
        }
    }
}
