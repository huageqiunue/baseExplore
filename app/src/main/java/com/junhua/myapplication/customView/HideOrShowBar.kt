package com.seewo.easinote.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.junhua.myapplication.R
import kotlin.math.abs


/**
 * 隐藏工具栏的bar
 */
class HideOrShowBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    /** 判断Toolbar是否还显示上屏(默认显示) */
    private var isToolbarShowing: Boolean = true

    companion object {
        const val DEFAULT_ANIMATE_DURATION = 500
    }

    /** 外部事件 */
    var invokeClickListener: OnClickListener? = null

    /** 动画延迟时间 */
    private var animateDuration: Int = DEFAULT_ANIMATE_DURATION

    /** 上次触发点击时间 */
    private var lastClickTime: Long = 0

    /** 指示图标 */
    private val barArrowImg = ImageView(context)

    private lateinit var barArrowImgLayoutParams: LayoutParams

    /** 锚点View，目前限定为Toolbar */
    private var targetView: ViewGroup? = null

    var arrowSrcForToolbarVisible: Int = R.drawable.ic_launcher_background
    var arrowSrcForToolbarHide: Int = R.drawable.ic_launcher_background

    private var mToolbarRootShowAnim: ObjectAnimator? = null
    private var mToolbarRootHideAnim: ObjectAnimator? = null

    private var mToolbarHideOrShowRootShowAnim: ObjectAnimator? = null
    private var mToolbarHideOrShowRootHideAnim: ObjectAnimator? = null

    @Volatile
    private var mIsHideOrShowToolbarAnimRunning = false

    init {
        if (attrs != null) {
            //解析自定义属性
            val typedArray: TypedArray =
                context.obtainStyledAttributes(attrs, R.styleable.HideOrShowBarArrowViewStyle)
            val arrowHeight =
                typedArray.getDimension(R.styleable.HideOrShowBarArrowViewStyle_arrowHeight, 14.7f)
            val arrowWidth =
                typedArray.getDimension(R.styleable.HideOrShowBarArrowViewStyle_arrowWidth, 77.3f)
            val arrowMarginTop =
                typedArray.getDimension(
                    R.styleable.HideOrShowBarArrowViewStyle_arrowMarginTop,
                    14.7f
                )

            animateDuration =
                typedArray.getInteger(
                    R.styleable.HideOrShowBarArrowViewStyle_animateDuration,
                    DEFAULT_ANIMATE_DURATION
                )
            arrowSrcForToolbarVisible = typedArray.getResourceId(
                R.styleable.HideOrShowBarArrowViewStyle_arrowSrcForToolbarVisible,
                R.drawable.ic_launcher_background
            )
            arrowSrcForToolbarHide = typedArray.getResourceId(
                R.styleable.HideOrShowBarArrowViewStyle_arrowSrcForToolbarHide,
                R.drawable.ic_launcher_background
            )
            barArrowImg.setImageResource(arrowSrcForToolbarVisible)
            barArrowImgLayoutParams = LayoutParams(arrowWidth.toInt(), arrowHeight.toInt())
                .apply {
                    setMargins(0, arrowMarginTop.toInt(), 0, 0)
                    addRule(CENTER_HORIZONTAL)
                }
            //设置Arrow属性
//            barArrowImg.apply {
//                updateLayoutParams<LayoutParams> {
//                    width = arrowWidth.toInt()
//                    height = arrowHeight.toInt()
//                    setMargins(0, arrowMarginTop.toInt(), 0, 0)
//                }
//                setImageResource(arrowSrcForToolbarVisible)
//            }
            typedArray.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        addView(barArrowImg, barArrowImgLayoutParams)
    }

    /**
     * 点击防抖
     */
    private fun clickEnable(): Boolean {
        val nowTime = System.currentTimeMillis()
        if (abs(nowTime - lastClickTime) < 100) {
            return false
        }
        lastClickTime = nowTime
        return true
    }

    /**
     * toolbar展示和隐藏
     */
    private fun onChangeToolbarRootShow() {
        Log.e("jiahua", "test out");
        if (isToolbarShowing) {
            startToolbarHideAnim()
        } else {
            startToolbarShowAnim()
        }
    }

    private fun startToolbarShowAnim() {
        onBeforeBarAnimateStart?.invoke(true)
        if (null == mToolbarRootShowAnim) {
            mToolbarRootShowAnim = ObjectAnimator.ofFloat(
                targetView,
                "translationY",
                resources.getDimension(R.dimen.note_toolbar_anim_translate),
                0f
            )
            mToolbarRootShowAnim!!.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    Log.e("jiahua", "test out111");
                    onBarAnimateEnd?.invoke(true)
                    isToolbarShowing = true
                    barArrowImg.setImageResource(arrowSrcForToolbarVisible)
                    mIsHideOrShowToolbarAnimRunning = false
                }
            })
            mToolbarRootShowAnim!!.duration = animateDuration.toLong()
            mToolbarHideOrShowRootShowAnim = ObjectAnimator.ofFloat(
                this,
                "translationY",
                resources.getDimension(R.dimen.note_toolbar_anim_translate),
                0f
            )
            mToolbarHideOrShowRootShowAnim!!.duration = animateDuration.toLong()
        }
        if (mToolbarHideOrShowRootShowAnim!!.isRunning) {
            return
        }
        mToolbarRootShowAnim?.start()
        mToolbarHideOrShowRootShowAnim?.start()
        mIsHideOrShowToolbarAnimRunning = true
    }

    private fun startToolbarHideAnim() {
        onBeforeBarAnimateStart?.invoke(false)
        if (null == mToolbarRootHideAnim) {
            mToolbarRootHideAnim = ObjectAnimator.ofFloat(
                this,
                "translationY",
                0f,
                48f
            )
            mToolbarRootHideAnim!!.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    Log.e("jiahua", "test out12111");
                    onBarAnimateEnd?.invoke(false)
                    isToolbarShowing = false
                    barArrowImg.setImageResource(arrowSrcForToolbarHide)
                    mIsHideOrShowToolbarAnimRunning = false
                }
            })
            mToolbarRootHideAnim!!.duration = animateDuration.toLong()
            mToolbarHideOrShowRootHideAnim = ObjectAnimator.ofFloat(
                this,
                "translationY",
                0f,
                48f
            )
            mToolbarHideOrShowRootHideAnim!!.duration = animateDuration.toLong()
        }
        if (mToolbarHideOrShowRootHideAnim!!.isRunning) {
            return
        }
        mToolbarRootHideAnim!!.start()
        mToolbarHideOrShowRootHideAnim!!.start()
        mIsHideOrShowToolbarAnimRunning = true
    }

    /**
     * 动画结束处理事件
     */
    var onBarAnimateEnd: ((isShow: Boolean) -> Unit)? = null

    /**
     * 动画开始之前处理事件
     */
    var onBeforeBarAnimateStart: ((isShow: Boolean) -> Unit)? = null


    /**
     * 处理分屏适配(vidis)
     */
//    private fun resetShowOrHideToolbarLayoutMargin(left: Int) {
//        if (visibility == GONE) {
//            return
//        }
//        val mToolbarViewHideIconMarginBottom = 0
//        Log.d("msg", "mToolbarViewHideIconMarginBottom:$mToolbarViewHideIconMarginBottom")
//        updateLayoutParams<LayoutParams> {
//            setMargins(left, 0, 0, mToolbarViewHideIconMarginBottom)
//            removeRule(CENTER_HORIZONTAL)
//        }
//    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!clickEnable()) {
            return false
        }
        val x = event.x.toInt()
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {}
            MotionEvent.ACTION_UP -> if (x + left < right && y + top < bottom) {
                invokeClickListener?.onClick(this)
                onChangeToolbarRootShow()
            }
        }
        return true
    }
}
