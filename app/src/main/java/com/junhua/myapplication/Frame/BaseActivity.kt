package com.junhua.myapplication.Frame

import android.os.Bundle
import android.os.PersistableBundle
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
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(mBinding.root)
        mBinding.initViews()
    }
    abstract fun VB.initViews()
}