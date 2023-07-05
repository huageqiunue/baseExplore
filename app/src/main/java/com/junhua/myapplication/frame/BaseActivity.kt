package com.junhua.myapplication.frame

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * activity基类
 */
abstract class BaseActivity<VB : ViewBinding>(
    val block: (LayoutInflater) -> VB
) : AppCompatActivity() {

    private val mBinding by lazy { block(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.init()
    }

    /**
     * 初始化
     */
    private fun VB.init(){
        initConfig()
        initViews()
        initListener()
    }

    /**
     * 初始化View
     */
    abstract fun VB.initViews()

    /**
     * 初始化监听
     */
    abstract fun VB.initListener()

    /**
     * 初始化配置内容
     */
    abstract fun VB.initConfig()

}