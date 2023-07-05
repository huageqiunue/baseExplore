package com.junhua.myapplication.customView.fish

import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.animation.LinearInterpolator
import kotlin.math.abs
import kotlin.math.sin

/**
 * 锦鲤Drawable
 */
class FishDrawable : Drawable() {
    private var mPaint: Paint? = null
    private var mPath: Path? = null
    private var currentValue = 0f

    companion object {
        const val OTHER_ALPHA = 1000
        const val HEAD_RADIUS = 50f

    }

    private var fishMainAngle = 90f

    /**
     * 小鱼点击尾巴加速摆动
     */
    var frequance = 1f

    fun setFishMainAngle(fishMainAngle: Float) {
        this.fishMainAngle = fishMainAngle
    }

    //鱼的中心
    var middlePoint: PointF? = null
        private set

    //鱼身长度
    private val BODY_LENGTH = HEAD_RADIUS * 3.2f

    //寻找鱼鳍的初始点
    private val FIND_FINS_LENGTH = 0.9f * HEAD_RADIUS

    //鱼鳍的长度
    private val FINS_LENGTH = 1.3f * HEAD_RADIUS

    //大圆半径
    private val BIG_CIRCLE_RADIUS = 0.7f * HEAD_RADIUS

    //中圆半径
    private val MIDDLE_CIRCLE_RADIUS = 0.6f * BIG_CIRCLE_RADIUS

    //小圆半径
    private val SMALL_CIRCLE_RADIUS = 0.4f * MIDDLE_CIRCLE_RADIUS

    //寻找尾部中圆圆心线长
    private val FIND_MIDDLE_CIRCLE_LENGTH = BIG_CIRCLE_RADIUS * 1.6f

    //寻找小圆
    private val FIND_SMALL_CIRCLE_LENGTH = MIDDLE_CIRCLE_RADIUS * 3.1f

    //寻找三角形
    private val FIND_TRIANGLE = MIDDLE_CIRCLE_RADIUS * 2.7f

    //眼镜半径
    private val EYE_CIRCLE = HEAD_RADIUS * 0.1f

    //鱼头坐标
    var headPoint: PointF? = null

    init {
        init()
    }

    private fun init() {
        mPaint = Paint()
        mPath = Path()
        mPaint!!.style = Paint.Style.FILL
        mPaint!!.isAntiAlias = true
        mPaint!!.isDither = true
        mPaint!!.setARGB(OTHER_ALPHA, 244, 92, 71)
        middlePoint = PointF(4.19f * HEAD_RADIUS, 4.19f * HEAD_RADIUS)
        val valueAnimator = ValueAnimator.ofFloat(0f, 360f)
        //动画周期
        valueAnimator.duration = 2000
        //重复模式
        valueAnimator.repeatMode = ValueAnimator.RESTART
        //重复次数
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        //插值器
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.addUpdateListener { animation ->
            currentValue = animation.animatedValue as Float
            invalidateSelf()
        }
        valueAnimator.start()
    }

    override fun draw(canvas: Canvas) {
        //鱼的初始角度
        val fishAngle = (fishMainAngle + sin(Math.toRadians((currentValue * frequance).toDouble())) * 5).toFloat()
        //鱼头的圆心坐标
        headPoint = calculatePoint(middlePoint, BODY_LENGTH, fishAngle)

        //画鱼头
        canvas.drawCircle(headPoint!!.x, headPoint!!.y, HEAD_RADIUS, mPaint!!)
        //画右鱼鳍
        val rightFinsPoint = calculatePoint(headPoint, FIND_FINS_LENGTH, fishAngle - 130)
        makeFins(canvas, rightFinsPoint, fishAngle, true)
        //画左鱼鳍
        val leftFinsPoint = calculatePoint(headPoint, FIND_FINS_LENGTH, fishAngle + 130)
        makeFins(canvas, leftFinsPoint, fishAngle, false)
        //画大节支
        val bodyBottomCenterPoint = calculatePoint(headPoint, BODY_LENGTH, fishAngle - 180)
        val smallCenterPoint = makeSegment(
            canvas, bodyBottomCenterPoint, BIG_CIRCLE_RADIUS, MIDDLE_CIRCLE_RADIUS, FIND_MIDDLE_CIRCLE_LENGTH,
            fishAngle, true
        )
        //画小节支
        makeSegment(
            canvas, smallCenterPoint, MIDDLE_CIRCLE_RADIUS, SMALL_CIRCLE_RADIUS, FIND_SMALL_CIRCLE_LENGTH,
            fishAngle, false
        )
        val findEdgeLength =
            abs(sin(Math.toRadians((currentValue * frequance).toDouble())) * BIG_CIRCLE_RADIUS)
                .toFloat()
        //画尾巴
        makeTriangle(canvas, smallCenterPoint, FIND_TRIANGLE, findEdgeLength, fishAngle)
        makeTriangle(canvas, smallCenterPoint, FIND_TRIANGLE - 10, findEdgeLength - 10, fishAngle)
        //画身体
        makeBody(canvas, headPoint, fishAngle)
        //画眼睛
        makeEyes(canvas, headPoint, fishAngle)
    }

    /**
     *
     * @param canvas
     * @param headPoint
     * @param fishAngle
     */
    private fun makeEyes(canvas: Canvas, headPoint: PointF?, fishAngle: Float) {
        val leftEye = calculatePoint(headPoint, HEAD_RADIUS, fishAngle + 45)
        val rightEye = calculatePoint(headPoint, HEAD_RADIUS, fishAngle - 45)
        canvas.drawCircle(leftEye.x, leftEye.y, EYE_CIRCLE, mPaint!!)
        canvas.drawCircle(rightEye.x, rightEye.y, EYE_CIRCLE, mPaint!!)
    }

    /**
     *
     * @param canvas
     * @param headPoint
     * @param fishAngle
     */
    private fun makeBody(canvas: Canvas, headPoint: PointF?, fishAngle: Float) {
        val topLeft = calculatePoint(headPoint, HEAD_RADIUS, fishAngle + 90)
        val topRight = calculatePoint(headPoint, HEAD_RADIUS, fishAngle - 90)
        val bottomCenterPoint = calculatePoint(headPoint, BODY_LENGTH, fishAngle - 180)
        val bottomLeft = calculatePoint(bottomCenterPoint, BIG_CIRCLE_RADIUS, fishAngle + 90)
        val bottomRight = calculatePoint(bottomCenterPoint, BIG_CIRCLE_RADIUS, fishAngle - 90)
        val controlLeft = calculatePoint(headPoint, BODY_LENGTH * 0.56f, fishAngle + 140)
        val controlRight = calculatePoint(headPoint, BODY_LENGTH * 0.56f, fishAngle - 140)
        mPath!!.reset()
        mPath!!.moveTo(topLeft.x, topLeft.y)
        mPath!!.quadTo(controlLeft.x, controlLeft.y, bottomLeft.x, bottomLeft.y)
        mPath!!.lineTo(bottomRight.x, bottomRight.y)
        mPath!!.quadTo(controlRight.x, controlRight.y, topRight.x, topRight.y)
        canvas.drawPath(mPath!!, mPaint!!)
    }

    /**
     *
     * @param canvas
     * @param startPoint
     * @param fishAngle
     */
    private fun makeTriangle(
        canvas: Canvas,
        startPoint: PointF,
        findTriangle: Float,
        bigCircleLength: Float,
        fishAngle: Float
    ) {
        var tr = 0f
        tr = (fishAngle + Math.sin(Math.toRadians((currentValue * 2 * frequance).toDouble())) * 30).toFloat()
        //三角形底边中心坐标
        val centerPoint = calculatePoint(startPoint, findTriangle, tr - 180)
        //得到三角形的两个点
        val bottomLeft = calculatePoint(centerPoint, bigCircleLength, tr + 90)
        val bottomRight = calculatePoint(centerPoint, bigCircleLength, tr - 90)
        mPath!!.reset()
        mPath!!.moveTo(startPoint.x, startPoint.y)
        mPath!!.lineTo(bottomLeft.x, bottomLeft.y)
        mPath!!.lineTo(bottomRight.x, bottomRight.y)
        canvas.drawPath(mPath!!, mPaint!!)
    }

    /**
     *
     * @param canvas
     * @param startPoint
     * @param fishAngle
     * @return
     */
    private fun makeSegment(
        canvas: Canvas, startPoint: PointF, bigRadius: Float, smallRadius: Float,
        findLength: Float, fishAngle: Float, hasBigCircle: Boolean
    ): PointF {
        var segmentAngle = 0f
        segmentAngle = if (hasBigCircle) {
            (fishAngle + Math.cos(Math.toRadians((currentValue * 2 * frequance).toDouble())) * 15).toFloat()
        } else {
            (fishAngle + Math.sin(Math.toRadians((currentValue * 2 * frequance).toDouble())) * 30).toFloat()
        }
        //梯形上底圆心
        val upperPoint = calculatePoint(startPoint, findLength, segmentAngle - 180)
        //梯形4个点
        val bottomLeftPoint = calculatePoint(startPoint, bigRadius, segmentAngle - 90)
        val bottomRightPoint = calculatePoint(startPoint, bigRadius, segmentAngle + 90)
        val upperLeftPoint = calculatePoint(upperPoint, smallRadius, segmentAngle - 90)
        val upperRightPoint = calculatePoint(upperPoint, smallRadius, segmentAngle + 90)
        if (hasBigCircle) {
            //画大圆
            canvas.drawCircle(startPoint.x, startPoint.y, bigRadius, mPaint!!)
        }
        //画小圆
        canvas.drawCircle(upperPoint.x, upperPoint.y, smallRadius, mPaint!!)
        //画梯形
        mPath!!.reset()
        mPath!!.moveTo(bottomLeftPoint.x, bottomLeftPoint.y)
        mPath!!.lineTo(bottomRightPoint.x, bottomRightPoint.y)
        mPath!!.lineTo(upperRightPoint.x, upperRightPoint.y)
        mPath!!.lineTo(upperLeftPoint.x, upperLeftPoint.y)
        canvas.drawPath(mPath!!, mPaint!!)
        return upperPoint
    }

    /**
     * 画鱼鳍
     * @param canvas
     * @param startPoint
     * @param fishAngle
     * @param isRight
     */
    private fun makeFins(canvas: Canvas, startPoint: PointF, fishAngle: Float, isRight: Boolean) {
        val controlAngle = 115f
        val endPoint = calculatePoint(startPoint, FINS_LENGTH, fishAngle - 180)
        mPath!!.reset()
        mPath!!.moveTo(startPoint.x, startPoint.y)
        //控制点
        val controlPoint = calculatePoint(
            startPoint, FINS_LENGTH * 1.8f,
            if (isRight) fishAngle - controlAngle else fishAngle + controlAngle
        )
        mPath!!.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y)
        canvas.drawPath(mPath!!, mPaint!!)
    }

    /**
     * 求关键坐标点的核心算法
     * @param startPoint 相对点的坐标
     * @param length 相对点到要求的点之间的直线距离
     * @param angle 和fishAngle之间的角度
     * @return
     */
    fun calculatePoint(startPoint: PointF?, length: Float, angle: Float): PointF {
        //x
        val deltaX = (Math.cos(Math.toRadians(angle.toDouble())) * length).toFloat()
        //Y
        val deltaY = (Math.sin(Math.toRadians((angle + 180).toDouble())) * length).toFloat()
        return PointF(startPoint!!.x + deltaX, startPoint.y + deltaY)
    }

    override fun setAlpha(alpha: Int) {
        mPaint!!.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint!!.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun getIntrinsicHeight(): Int {
        return (8.38f * HEAD_RADIUS).toInt()
    }

    override fun getIntrinsicWidth(): Int {
        return (8.38f * HEAD_RADIUS).toInt()
    }
}