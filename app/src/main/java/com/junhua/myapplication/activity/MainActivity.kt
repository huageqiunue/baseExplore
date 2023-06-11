package com.junhua.myapplication.activity

import com.junhua.myapplication.databinding.ActivityMainBinding
import com.junhua.myapplication.frame.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun ActivityMainBinding.initViews() {
        initListener()
    }

    private fun initListener() {

    }

}