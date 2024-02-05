package com.junhua.myapplication.customView

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

/**
 * 支持单选按钮
 */
class SingleSelectButtonGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {
    private val buttons = arrayListOf<Button>()
    private var mOnButtonClickBlock: ((Button, Boolean) -> Unit)? = null
    private var mSelectedButton: Button? = null
    private var mIsOnDoubleClick: Boolean = false

    init {
        orientation = HORIZONTAL
        init()
    }

    private fun init() {
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            // 检查子视图是否是一个Button组件
            if (childView is Button) {
                addButton(childView)
            }
        }
    }

    private fun addButton(button: Button) {
        buttons.add(button)
        button.setOnClickListener(this)
    }

    fun setOnButtonClickListener(onButtonClickBlock: ((Button, Boolean) -> Unit)) {
        mOnButtonClickBlock = onButtonClickBlock
    }

    override fun onClick(v: View?) {
        val clickedButton = v as Button
        if (mSelectedButton != null && mSelectedButton == clickedButton) {
            // 重复选中按钮
            mIsOnDoubleClick = !mIsOnDoubleClick
            mOnButtonClickBlock?.invoke(clickedButton, mIsOnDoubleClick)
        } else {
            mSelectedButton?.isSelected = false
            mSelectedButton = clickedButton
            mSelectedButton?.isSelected = true
            mIsOnDoubleClick = false
            mOnButtonClickBlock?.invoke(clickedButton, false)
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean = true
}