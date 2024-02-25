package com.example.fivechessgame.frame

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * 有lifecycle的Fragment
 */
abstract class LifecycleFragment<VB : ViewBinding>(bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> VB) :
    BaseFragment<VB>(bindingInflate) {

    /**
     * 生成Tag
     */
    abstract fun receiveTag(): String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(receiveTag(), "on Create")
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