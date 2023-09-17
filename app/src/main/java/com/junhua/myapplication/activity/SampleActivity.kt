package com.junhua.myapplication.activity

import android.util.Log
import com.junhua.myapplication.databinding.ActivitySampleBinding
import com.junhua.myapplication.frame.BaseViewModelActivity
import com.junhua.myapplication.viewModel.SampleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleActivity : BaseViewModelActivity<ActivitySampleBinding, SampleViewModel>(ActivitySampleBinding::inflate, SampleViewModel::class.java) {
    override fun ActivitySampleBinding.initViews() {
        viewModel.test()
    }

    override fun ActivitySampleBinding.initListener() {
    }

}