package com.capstone.berrets.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone.berrets.R
import com.capstone.berrets.view.onBoard.OnboardActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.statusBarColor = resources.getColor(R.color.backgroundSecondary)
        window.navigationBarColor = resources.getColor(R.color.backgroundSecondary)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                )

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, OnboardActivity::class.java))
            finish()
        }, 2000)
    }
}