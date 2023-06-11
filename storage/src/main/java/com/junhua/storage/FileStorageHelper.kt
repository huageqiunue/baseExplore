package com.junhua.storage

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.junhua.storage.api.IShareStorageApi
import java.io.OutputStream

class FileStorageHelper: IShareStorageApi {
    override fun saveFileToOutPutStream(
        context: Context,
        displayName: String,
        subPath: String,
        mimeType: String,
        saveFileBlock: (OutputStream) -> Unit
    ): String? {

        return null
    }

    override fun getParentAbsolutePath(subPath: String) {
        TODO("Not yet implemented")
    }

    override fun createAndGetParentAbsolutePath(context: Context, subPath: String): String {
        TODO("Not yet implemented")
    }

    override fun deleteFileByAbsolutePath(
        activity: AppCompatActivity,
        absolutePath: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteFileByUri(activity: AppCompatActivity, uri: Uri): Boolean {
        TODO("Not yet implemented")
    }

    override fun searchUriByAbsolutePath(absolutePath: String, uri: Uri): Boolean {
        TODO("Not yet implemented")
    }

}