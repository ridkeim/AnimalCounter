package ru.ridkeim.animalcounter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _crowCount = MutableLiveData<Int>()
    val crowCount : LiveData<Int>
    get() = _crowCount
    private val _catCount = MutableLiveData<Int>()
    val catCount : LiveData<Int>
    get() = _catCount

    init {
        _crowCount.value = 0
        _catCount.value = 0
    }

    fun onCatFound(){
        _catCount.value = _catCount.value?.plus(1)
    }

    fun onCrowFound(){
        _crowCount.value = _crowCount.value?.plus(1)
    }

}