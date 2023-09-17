package com.junhua.myapplication.frame

import android.util.Log
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    override fun onCleared() {
        super.onCleared()
    }
}