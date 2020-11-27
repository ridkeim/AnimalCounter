package ru.ridkeim.animalcounter

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import java.lang.IllegalArgumentException

class MainActivityViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver{

    companion object {
        private const val KEY_SETTINGS = "settings"
        private const val KEY_CROW_COUNT = "crow_count"
        private const val KEY_CAT_COUNT = "cat_count"
    }

    private val context : Context
    private val prefs : SharedPreferences
    private val _crowCount = MutableLiveData<Int>()
    val crowCount : LiveData<String>
    get() = Transformations.map(_crowCount){
        context.resources.getQuantityString(R.plurals.numberOfCrows, it, it)
    }
    private val _catCount = MutableLiveData<Int>()
    val catCount : LiveData<String>
    get() = Transformations.map(_catCount){
        context.resources.getQuantityString(R.plurals.numberOfCats, it, it)
    }
    init {
        context = getApplication()
        prefs = context.getSharedPreferences(KEY_SETTINGS,Context.MODE_PRIVATE)
        _crowCount.value = prefs.getInt(KEY_CROW_COUNT,0)
        _catCount.value = prefs.getInt(KEY_CAT_COUNT,0)
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
                return MainActivityViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    fun onCatFound(){
        _catCount.value = _catCount.value?.plus(1)
    }

    fun onCrowFound(){
        _crowCount.value = _crowCount.value?.plus(1)
    }

    fun resetCounters(){
        _crowCount.value = 0
        _catCount.value = 0
        saveCounters()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun saveCounters(){
        val prefsEditor = prefs.edit()
        _catCount.value?.let { prefsEditor.putInt(KEY_CAT_COUNT, it) }
        _crowCount.value?.let { prefsEditor.putInt(KEY_CROW_COUNT, it) }
        prefsEditor.apply()
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("viewModel","viewModelCleared")
    }

}