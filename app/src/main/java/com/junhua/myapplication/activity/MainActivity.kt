package com.junhua.myapplication.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.core.net.toUri
import com.example.storage.SafParams
import com.example.storage.openSafForCallback
import com.junhua.myapplication.databinding.ActivityMainBinding
import com.junhua.myapplication.frame.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun ActivityMainBinding.initViews() {
        initListener()
    }

    private fun initListener() {
        mBinding.openSaf.setOnClickListener {
            Log.e("jiahua", "test out")
            openSafForCallback(SafParams("1".toUri())) {
                onSucceed {
                    Log.e("jiahua", "test ou--t${it.joinToString()}");
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
}