package com.example.flagquiz.utils

import androidx.fragment.app.Fragment
import com.example.flagquiz.R
import com.tapadoo.alerter.Alerter


fun Fragment.alerter(message:String){
    Alerter.create(requireActivity())
        .setText(message)
        .setBackgroundColorRes(R.color.fon)
        .setDuration(2000)
        .show()
}