package com.junhua.storage.api

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.junhua.storage.StorageManage
import java.io.InputStream
import java.io.OutputStream

interface IShareStorageApi {
    /**
     * 保存文件到公共目录
     * @param displayName 存储的文件名
     * @param subPath 子路径
     * @param mimeType 文件的MimeType
     * @param saveFileBlock 写入数据到[outputStream]的闭包
     *
     * @return 保存文件成功返回文件名，保存失败返回null
     */
    fun saveFileToOutPutStream(
        context: Context,
        displayName: String,
        subPath: String = StorageManage.defaultPublicDirectorySubPath,
        mimeType: String = StorageManage.defaultPublicDirectoryMimeType,
        saveFileBlock: (OutputStream) -> Unit
    ): String?

    /**
     * 根据[inputStream]保存文件到公共目录
     * @param displayName 存储的文件名
     * @param subPath 子路径
     * @param mimeType 文件的MimeType
     *
     * @return 保存文件成功返回文件名，保存失败返回null
     */
    fun saveFileFromInputStream(
        context: Context,
        inputStream: InputStream,
        displayName: String,
        subPath: String,
        mimeType: String,
    ): String? {
        return saveFileToOutPutStream(context, displayName, subPath, mimeType) { outputStream ->
            var read: Int
            val buffer = ByteArray(2048)
            while (inputStream.read(buffer).also { read = it } != -1) {
                outputStream.write(buffer, 0, read)
            }
        }
    }

    /**
     * 获取公共目录存放的绝对路径，路径为:根路径/[subPath]子路径
     */
    fun getParentAbsolutePath(subPath: String = StorageManage.defaultPublicDirectorySubPath)

    /**
     * 获取公共目录存放的绝对路径，路径为:根路径/[subPath]子路径,若路径不存在则创建
     */
    fun createAndGetParentAbsolutePath(context: Context, subPath: String): String

    /**
     * 根据[absolutePath]绝对路径删除公共目录文件
     */
    fun deleteFileByAbsolutePath(activity: AppCompatActivity, absolutePath: String): Boolean

    /**
     * 根据[Uri]删除公共目录的文件
     */
    fun deleteFileByUri(activity: AppCompatActivity, uri: Uri): Boolean

    /**
     * 根据文件[absolutePath]绝对路径从多媒体数据库中查询公共目录中的[uri]
     */
    fun searchUriByAbsolutePath(absolutePath: String, uri: Uri): Boolean
}