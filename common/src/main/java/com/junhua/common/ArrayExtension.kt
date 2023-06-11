package com.junhua.common


/**
 * 判断数值是否在Array中
 * @param compareValue 目标值
 * @param compareValues 用作比较的Array可变参数
 */
fun <T> baseTypeIsIn(
    compareValue: T,
    vararg compareValues: T
): Boolean {
    if (compareValues.isEmpty()) {
        return false
    }
    return compareValues.any {
        it == compareValue
    }
}


