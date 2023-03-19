package com.junhua.myapplication.Frame

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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
        mBinding.initViews()
    }

    abstract fun VB.initViews()

}