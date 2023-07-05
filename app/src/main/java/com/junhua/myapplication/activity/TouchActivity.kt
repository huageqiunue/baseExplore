package com.junhua.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.junhua.myapplication.databinding.ActivityTouchBinding
import com.junhua.myapplication.frame.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TouchActivity : BaseActivity<ActivityTouchBinding>(ActivityTouchBinding::inflate) {
    override fun ActivityTouchBinding.initViews() {
        TODO("Not yet implemented")
    }

    override fun ActivityTouchBinding.initListener() {
        TODO("Not yet implemented")
    }

    override fun ActivityTouchBinding.initConfig() {
        TODO("Not yet implemented")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }
}