package com.junhua.myapplication.frame

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Activity基类
 */
abstract class BaseViewModelActivity<VB : ViewDataBinding, VM : ViewModel>(
    val block: (LayoutInflater) -> VB, var viewModelClass: Class<VM>
) : AppCompatActivity() {
    val mBinding by lazy { block(layoutInflater) }
    val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(mBinding.root)
        mBinding.init()
    }

    /**
     * 初始化
     */
    private fun VB.init() {
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

}