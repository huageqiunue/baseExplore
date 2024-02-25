package com.example.fivechessgame.activity

import com.example.fivechessgame.frame.BaseActivity
import com.example.fivechessgame.lifecycle.observer.LogEventObserver
import com.junhua.myapplication.databinding.ActivityMainBinding
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