package com.namazpadho.app

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv = TextView(this)
        tv.text = "Assalamu Alaikum, Namaz Padho"
        tv.textSize = 24f
        tv.setPadding(40, 120, 40, 40)
        setContentView(tv)
    }
}
