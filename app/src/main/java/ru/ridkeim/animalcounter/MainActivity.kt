package ru.ridkeim.animalcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import ru.ridkeim.animalcounter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView( this, R.layout.activity_main)
        val viewModel: MainActivityViewModel = ViewModelProvider(
            this,
            MainActivityViewModel.Factory(application)
        ).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewModel)
    }
}