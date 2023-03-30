package com.junhua.myapplication.frame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * 有lifecycle的Fragment
 */
abstract class LifecycleFragment<VB : ViewBinding>(bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> VB) :
    BaseFragment<VB>(bindingInflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}