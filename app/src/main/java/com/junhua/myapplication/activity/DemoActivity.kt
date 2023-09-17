package com.junhua.myapplication.activity

import com.junhua.myapplication.databinding.ActivityDemoBinding
import com.junhua.myapplication.frame.BaseViewModelActivity
import com.junhua.myapplication.viewModel.DemoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : BaseViewModelActivity<ActivityDemoBinding, DemoViewModel>(ActivityDemoBinding::inflate, DemoViewModel::class.java) {
    override fun ActivityDemoBinding.initViews() {
        TODO("Not yet implemented")
    }

    override fun ActivityDemoBinding.initListener() {
        TODO("Not yet implemented")
    }

}