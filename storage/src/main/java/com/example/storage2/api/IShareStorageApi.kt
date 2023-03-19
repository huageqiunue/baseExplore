package com.example.storage2.api

interface IShareStorageApi {
    fun saveFileToOutPutStream():String?
    fun getParentAbsolutePath()
    fun createAndGetParentAbsolutePath()
    fun deleteFileByAbsolutePath()
    fun searchUriByAbsolutePath()
}