package com.junhua.myapplication.Frame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Fragment基类
 */
abstract class BaseFragment< T : ViewBinding>: Fragment() {
    private lateinit var _binding: T
    protected val mBinding get() = _binding;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected abstract fun getViewBinding(): T
}