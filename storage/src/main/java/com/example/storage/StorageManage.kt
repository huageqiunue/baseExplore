package com.example.storage


/**
 * 存储管理类
 */
object StorageManage {

    enum class MediaType {
        MUSIC,
        FILES,
        DOWNLOADS,
        MOVIES,
        AUDIO,
        IMAGES
    }

    var defaultPublicDirectorySubPath: String = "";
    var defaultPublicDirectoryMimeType: String = "*/*"

    fun getSaf() {

    }



}