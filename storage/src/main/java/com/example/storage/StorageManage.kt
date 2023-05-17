package com.example.storage

import android.content.Context
import android.os.Environment
import com.example.storage.StorageUtils.sdk29AndUp


/**
 * 存储管理类
 */
object StorageManage {

    enum class MediaType {
        MUSIC,
        FILES,
        DOWNLOADS,
        AUDIO,
        IMAGES
    }

    /**
     * 外部存储路径
     */
    fun getExternal() {
        Environment.getExternalStorageDirectory()
        Environment.getDownloadCacheDirectory()
    }

    /**
     * 内部存储路径
     */
    fun getInner(context: Context) {
        context.cacheDir
//        context.externalCacheDirs
    }

//    val downlownds = MediaStore.Downloads.EXTERNAL_CONTENT_URI
//    val downlownds = MediaStore.Video.Media.
//    val downlownds = MediaStore.Files
//    val downlownds = MediaStore.Images
//    val downlownds = MediaStore.Audio

    var defaultPublicDirectorySubPath: String = "";
    var defaultPublicDirectoryMimeType: String = "*/*"

    fun getSaf() {
        sdk29AndUp {

        }
    }

}