package com.junhua.myapplication.activity

import android.graphics.*
import androidx.annotation.ColorInt
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.segmentation.subject.SubjectSegmentation
import com.google.mlkit.vision.segmentation.subject.SubjectSegmentationResult
import com.google.mlkit.vision.segmentation.subject.SubjectSegmenter
import com.google.mlkit.vision.segmentation.subject.SubjectSegmenterOptions
import com.junhua.common.showToast
import com.junhua.myapplication.R
import com.junhua.myapplication.databinding.ActivityOpencvTestBinding
import com.junhua.myapplication.frame.BaseActivity


/**
 * 测试openCv的Activity
 */
class OpencvTestActivity : BaseActivity<ActivityOpencvTestBinding>(ActivityOpencvTestBinding::inflate) {

    override fun ActivityOpencvTestBinding.initViews() {
    }

    override fun ActivityOpencvTestBinding.initListener() {
        objectSegmentationButton.setOnClickListener {
            val decodeResource = BitmapFactory.decodeResource(resources, R.mipmap.fish)
            val image = InputImage.fromBitmap(decodeResource, 0)
            val build = SubjectSegmenterOptions.Builder().enableMultipleSubjects(SubjectSegmenterOptions.SubjectResultOptions.Builder().enableConfidenceMask().build()).build()
            val segmentation: SubjectSegmenter = SubjectSegmentation.getClient(build)
            segmentation.process(image).addOnSuccessListener {
                showToast("个数为：${it.subjects.size}")
                this@OpencvTestActivity.runOnUiThread {
                    val resultBitmap = Bitmap.createBitmap(decodeResource.getWidth(), decodeResource.getHeight(), Bitmap.Config.ARGB_8888)
                    val maskBitmap = Bitmap.createBitmap(
                        maskColorsFromFloatBuffer(it, decodeResource.getWidth(), decodeResource.getHeight()),
                        decodeResource.width,
                        decodeResource.height,
                        Bitmap.Config.ARGB_8888
                    )
                    val canvas = Canvas(resultBitmap)
                    canvas.drawBitmap(decodeResource, 0f, 0f, null)
                    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
                    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN); // 设置合成模式为DST_IN
                    canvas.drawBitmap(maskBitmap, 0f, 0f, paint);
                    imageView.setImageBitmap(resultBitmap)
                }
            }
        }

        grebCutButton.setOnClickListener {
        }
    }

    @ColorInt
    private fun maskColorsFromFloatBuffer(subjectSegmentationResult: SubjectSegmentationResult, width: Int, height: Int): IntArray {
        @ColorInt val colors = IntArray(width * height)
        for (k in 0 until subjectSegmentationResult.subjects.size) {
            val subject = subjectSegmentationResult.subjects[k]
            val color = Color.RED
            val mask = subject.confidenceMask
            for (j in 0 until subject.height) {
                for (i in 0 until subject.width) {
                    if (mask!!.get() > 0.5) {
                        colors[(subject.startY + j) * width + subject.startX + i] = color
                    }
                }
            }
            // Reset [FloatBuffer] pointer to beginning, so that the mask can be redrawn if screen is
            // refreshed
            mask!!.rewind()
        }
        return colors
    }

    override fun ActivityOpencvTestBinding.initConfig() {
//        OpenCVLoader.initDebug()
    }
}