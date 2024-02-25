package com.example.fivechessgame.viewModel

import com.example.fivechessgame.frame.BaseViewModel
import com.example.fivechessgame.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SampleViewModel @Inject constructor(private val repository: DataRepository) : BaseViewModel() {
    fun test() {
        repository.test()
    }
}