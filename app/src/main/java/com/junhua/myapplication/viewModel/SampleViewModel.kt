package com.junhua.myapplication.viewModel

import com.junhua.myapplication.frame.BaseViewModel
import com.junhua.myapplication.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SampleViewModel @Inject constructor(private val repository: DataRepository) : BaseViewModel() {
    fun test() {
        repository.test()
    }
}