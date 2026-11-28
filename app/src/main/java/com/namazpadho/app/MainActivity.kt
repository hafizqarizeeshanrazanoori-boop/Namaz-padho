package com.namazpadho.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("namaz", Context.MODE_PRIVATE)
        setContent {
            var stage by remember { mutableStateOf(0) }
            var lang by remember { mutableStateOf(prefs.getInt("lang", 0)) }
            var first by remember { mutableStateOf(prefs.getString("first", "") ?: "") }
            val dir = if (lang in L.rtl) LayoutDirection.Rtl else LayoutDirection.Ltr
            CompositionLocalProvider(LocalLayoutDirection provides dir) {
                when (stage) {
                    0 -> SplashScreen {
                        stage = if (prefs.getBoolean("done", false)) 2 else 1
                    }
                    1 -> LoginScreen(lang, { lang = it }) { f, l ->
                        prefs.edit()
                            .putBoolean("done", true)
                            .putString("first", f)
                            .putString("last", l)
                            .putInt("lang", lang)
                            .apply()
                        first = f
                        stage = 2
                    }
                    else -> HomeScreen(lang, first)
                }
            }
        }
    }
}
