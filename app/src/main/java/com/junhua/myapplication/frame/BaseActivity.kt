package com.junhua.myapplication.frame

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


/**
 * activity基类
 */
abstract class BaseActivity<VB : ViewBinding>(
    val block: (LayoutInflater) -> VB
) : AppCompatActivity() {

    protected val mBinding by lazy { block(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.initViews()
    }
    abstract fun VB.initViews()
}