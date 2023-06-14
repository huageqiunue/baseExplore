package com.junhua.common

object FileUtil {

    /**
     * MIME映射表
     */
    val fileMimeTypeMap = hashMapOf<String, String>(
        "3gp" to "video/3gpp",
        "aab" to "video/3gpp",
        "aam" to "video/3gpp",
        "3gp" to "video/3gpp",
        "3gp" to "video/3gpp",
        "3gp" to "video/3gpp",
        "3gp" to "video/3gpp",
        "3gp" to "video/3gpp",
    )
    enum class MimeType(){
        THREEGP(),
        w,
    }
}