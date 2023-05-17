package com.junhua.myapplication.activity

import android.util.Log
import com.junhua.myapplication.databinding.ActivityMainBinding
import com.junhua.myapplication.frame.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun ActivityMainBinding.initViews() {
        initListener()
    }

    private fun initListener() {
        mBinding.openSaf.setOnClickListener {
            Log.e("jiahua", "test out")
//            openSafForCallback(SafParams("1".toUri())) {
//                onSucceed {
//                    Log.e("jiahua", "test ou--t${it.joinToString()}");
//                }
//            }
        }
    }

}