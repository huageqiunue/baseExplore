package com.junhua.myapplication.viewModel

import androidx.lifecycle.ViewModel
import com.junhua.myapplication.repository.DataRepository
import javax.inject.Inject

class DemoViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {
    fun test(){
        repository.test()
    }
}