package com.junhua.myapplication.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.junhua.myapplication.databinding.ActivityMainBinding
import com.junhua.myapplication.frame.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun ActivityMainBinding.initViews() {
        mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
}