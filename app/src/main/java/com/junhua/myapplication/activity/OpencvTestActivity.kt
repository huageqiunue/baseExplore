package com.junhua.myapplication.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.segmentation.Segmentation
import com.google.mlkit.vision.segmentation.selfie.SelfieSegmenterOptions
import com.google.mlkit.vision.segmentation.subject.SubjectSegmentation
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
        mlkitSegmentationButton.setOnClickListener {
            val decodeResource = BitmapFactory.decodeResource(resources, R.mipmap.img_1)
            val options = SelfieSegmenterOptions.Builder()
                .setDetectorMode(SelfieSegmenterOptions.SINGLE_IMAGE_MODE)
                .enableRawSizeMask()
                .setStreamModeSmoothingRatio(1f)
                .build()

            val segmenter = Segmentation.getClient(options)
            val image = InputImage.fromBitmap(decodeResource, 0)
            segmenter.process(image).addOnSuccessListener { result ->
                // 处理分割结果
                val bitmap = Bitmap.createBitmap(result.width, result.height, Bitmap.Config.ARGB_8888)
                val pixels = IntArray(result.width * result.height)
                result.buffer.rewind()
                result.buffer.asIntBuffer().get(pixels)
                bitmap.setPixels(pixels, 0, result.width, 0, 0, result.width, result.height)
                this@OpencvTestActivity.runOnUiThread {
                    imageView.setImageBitmap(bitmap)
                }
            }
                .addOnFailureListener { e ->
                    // 处理错误
                    e.printStackTrace()
                }
        }
        objectSegmentationButton.setOnClickListener {
            val decodeResource = BitmapFactory.decodeResource(resources, R.mipmap.fish)
            val image = InputImage.fromBitmap(decodeResource, 0)
            val build = SubjectSegmenterOptions.Builder().enableMultipleSubjects(SubjectSegmenterOptions.SubjectResultOptions.Builder().enableConfidenceMask().build()).build()
            val segmentation: SubjectSegmenter = SubjectSegmentation.getClient(build)
            segmentation.process(image).addOnSuccessListener {
                showToast("个数为：${it.subjects.size}")
                this@OpencvTestActivity.runOnUiThread {
                    imageView.setImageBitmap(it.subjects.get(0).bitmap)
                }
            }
        }
        grebCutButton.setOnClickListener {
        }
    }

    override fun ActivityOpencvTestBinding.initConfig() {
//        OpenCVLoader.initDebug()
    }
}