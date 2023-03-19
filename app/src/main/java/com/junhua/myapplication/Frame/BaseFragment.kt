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
abstract class BaseFragment<VB : ViewBinding>(val bindingInflate:(LayoutInflater, ViewGroup?, Boolean)->VB) : Fragment() {
    private lateinit var _binding: VB
    protected val mBinding get() = _binding;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflate(inflater, container, false)
        return _binding.root
    }

    /**
     * 初始化界面
     */
    abstract fun VB.initView()
}