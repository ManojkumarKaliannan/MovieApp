package com.backbase.assignment.ui.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.backbase.assignment.ui.utils.toast


abstract class BaseViewModel<N>(application: Application) : AndroidViewModel(application) {

    private val mApplication: Application = application
    private var mNavigator: N? = null


     fun putToast(msg: String) {
        mApplication.toast(msg)
    }

    fun getNavigator(): N {
        return mNavigator!!
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = navigator
    }
}