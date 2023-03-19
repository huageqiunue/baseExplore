package com.junhua.myapplication.Activity

import com.junhua.myapplication.Frame.BaseActivity
import com.junhua.myapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {


    override fun ActivityMainBinding.initViews() {
        mBinding.root.height
    }

}