package com.junhua.myapplication.learn

import android.content.Context
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomViewPage(context: Context): ViewPager(context) {
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return super.onInterceptTouchEvent(ev)
    }
}