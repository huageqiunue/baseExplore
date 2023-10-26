package com.junhua.myapplication.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<VB : ViewBinding>(val block: (LayoutInflater) -> VB) : DialogFragment() {
    val TAG: String = javaClass::class.java.name
    private val mBinding by lazy { block(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        initView(mBinding.root)
        return mBinding.root
    }


    override fun onStart() {
        super.onStart()
        initConfig(dialog)
    }

    abstract fun initView(view: View)

    abstract fun initConfig(dialog: Dialog?)

}