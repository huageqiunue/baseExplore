package com.example.storage.api

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import java.io.FileNotFoundException

class StorageUtils {
    companion object {
        const val OPEN_FILE_DESCRIPTOR_MODE_READ = "r"
    }

    /**
     * 按xxx(index).xxx的格式自动重命名文件
     */
    fun autoRenameFIleName(displayName: String, index: Int): String {
        val endIndex = displayName.lastIndexOf(".")
        if (endIndex == -1) {
            return "${displayName.substring(0, displayName.length)}($index)"
        }
        return "${displayName.substring(0, endIndex)}($index).${getExtraName(displayName)}"
    }

    /**
     * 获取后缀名
     */
    fun getExtraName(displayName: String): String {
        return "jpg"
    }

    /**
     * 检查[uri]是否有效
     */
    fun checkFileUriExists(context: Context, uri: Uri): Boolean {
        var isExists = false
        try {
            context.contentResolver.openFileDescriptor(uri, OPEN_FILE_DESCRIPTOR_MODE_READ)?.use {
                isExists = it.fileDescriptor.valid()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return isExists
    }

    /**
     * 通知媒体库刷新[uri]
     */
    fun notifyMediaStore(context: Context, uri: Uri) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).apply {
            data = uri
        }
        context.sendBroadcast(mediaScanIntent)
    }

    /**
     * sdk版本大于29,执行传入的[onSdk20AndUp]代码块
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q, lambda = 0)
    internal inline fun <T> sdk29AndUp(onSdk20AndUp: () -> T): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            onSdk20AndUp()
        } else null
    }
}