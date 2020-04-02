package com.eduardonunes.selectablelivedata.ui.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun View.changeVisibility(show: Boolean, typeHide: Int = View.GONE) {
    if (this.visibility == View.VISIBLE && show) return
    visibility = if (show) View.VISIBLE else typeHide
}