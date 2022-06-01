package com.backbase.assignment.ui.ui.dashboard

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.backbase.assignment.ui.ui.base.BaseNavigator
import com.backbase.assignment.ui.ui.base.BaseViewModel
import org.koin.core.KoinComponent

class MovieBoxViewModel(application: Application) :BaseViewModel<BaseNavigator>(application){
    var networkStatus=ObservableBoolean(true)
    private val _textPlaying = MutableLiveData<String>().apply {
        value = "This is Playing Now Fragment"
    }
    val textPlaying: LiveData<String> = _textPlaying

    private val _textPopular = MutableLiveData<String>().apply {
        value = "This is Popular Fragment"
    }
    val textPopular: LiveData<String> = _textPopular
}