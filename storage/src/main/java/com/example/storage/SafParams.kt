package com.example.storage

import android.net.Uri

/**
 * 打开类型
 */
enum class SafOpenType {
    ACTION_OPEN_DOCUMENT,
    ACTION_OPEN_DOCUMENT_TREE
}

/**
 * saf传递参数
 * @param startPosition 起始uri
 * @param safOpenType 打开类型
 * @param isNewTask 是否在新活动栈打开
 */
data class SafParams(
    val startPosition: Uri,
    val miniType: Int,
    val safOpenType: SafOpenType = SafOpenType.ACTION_OPEN_DOCUMENT,
    val isNewTask: Boolean = false
)