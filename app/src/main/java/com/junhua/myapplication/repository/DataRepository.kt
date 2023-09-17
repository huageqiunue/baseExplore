package com.junhua.myapplication.repository

import javax.inject.Inject


/**
 * 数据仓库
 */


class DataRepository @Inject constructor(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) {
    fun test() {
        localDataSource.test()
    }
}