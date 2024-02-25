package com.example.fivechessgame.activity

import com.junhua.myapplication.databinding.ActivityMainBinding
import com.junhua.myapplication.frame.BaseActivity
import com.junhua.myapplication.lifecycle.observer.LogEventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun ActivityMainBinding.initViews() {
    }

    override fun ActivityMainBinding.initListener() {
    }

    override fun ActivityMainBinding.initConfig() {
        bindLifecycle()
    }

    private fun bindLifecycle() {
        lifecycle.addObserver(LogEventObserver())
    }

}