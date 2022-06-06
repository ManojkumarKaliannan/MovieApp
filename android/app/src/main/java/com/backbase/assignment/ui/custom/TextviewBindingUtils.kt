package com.backbase.assignment.ui.custom

import android.widget.TextView
import androidx.databinding.BindingAdapter

    @BindingAdapter(value=["convertToPercentage"], requireAll=false)
    fun textPercentage(view: TextView, customImagePath: Double?) {
        view.text = customImagePath?.let { getCount(it) }
    }
    private fun getCount(count:Double): String {
        return (((count / 10) * 100).toLong()).toString()+"%"
    }
