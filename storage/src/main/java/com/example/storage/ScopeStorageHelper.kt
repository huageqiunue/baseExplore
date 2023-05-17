package com.example.storage

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.ContactsContract.Directory
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.example.storage.api.IShareStorageApi
import java.io.OutputStream

/**
 * 分区存储
 */
class ScopeStorageHelper : IShareStorageApi {

    lateinit var currentMediaType: StorageManage.MediaType
    override fun saveFileToOutPutStream(
        context: Context,
        displayName: String,
        subPath: String,
        mimeType: String,
        saveFileBlock: (OutputStream) -> Unit
    ): String? {
//        context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
//        val projection = arrayOf(media - database - columns - to - retrieve)
//        val selection = sql - where - clause - with - placeholder - variables
//        val selectionArgs = values - of - placeholder - variables
//        val sortOrder = sql - order - by - clause
//
//        context.contentResolver.query(
//            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
//            projection,
//            selection,
//            selectionArgs,
//            sortOrder
//        )?.use { cursor ->
//            while (cursor.moveToNext()) {
//                // Use an ID column from the projection to get
//                // a URI representing the media item itself.
//            }
//        }
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