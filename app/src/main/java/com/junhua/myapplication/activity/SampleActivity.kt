package com.junhua.myapplication.activity

import android.util.Log
import android.view.MotionEvent
import com.junhua.myapplication.databinding.ActivitySampleBinding
import com.junhua.myapplication.frame.BaseViewModelActivity
import com.junhua.myapplication.viewModel.SampleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleActivity : BaseViewModelActivity<ActivitySampleBinding, SampleViewModel>(ActivitySampleBinding::inflate, SampleViewModel::class.java) {
    var count = 0
    override fun ActivitySampleBinding.initViews() {
        viewModel.test()
        mBinding.title.eventBlock = object : (MotionEvent) -> Unit {
            override fun invoke(p1: MotionEvent) {
                when (p1.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        mBinding.s.setText("")
                        mBinding.s.append("\nmethod:onTouchEvent ACTION_DOWN ")
                        count++
                    }
                    MotionEvent.ACTION_POINTER_DOWN -> {
                        mBinding.s.append("\nmethod:onTouchEvent ACTION_POINTER_DOWN")
                        count++
                    }
                    MotionEvent.ACTION_MOVE -> {
//                Log.e("jiahua", "method:onTouchEvent ACTION_MOVE ")
                    }

                    MotionEvent.ACTION_POINTER_UP -> {
                        mBinding.s.append("\nmethod:onTouchEvent ACTION_POINTER_UP")
                        count--
                    }
                    MotionEvent.ACTION_UP -> {
                        mBinding.s.append("\nmethod:onTouchEvent ACTION_UP")
                        count--

                    }
                    MotionEvent.ACTION_CANCEL -> {
                        mBinding.s.append("\nmethod:onTouchEvent ACTION_CANCEL")
                        count--
                    }
                    else -> {}
                }
            }

        }
    }

    override fun ActivitySampleBinding.initListener() {
    }

}