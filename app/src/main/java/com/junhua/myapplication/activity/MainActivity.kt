package com.junhua.myapplication.activity

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

    }

}