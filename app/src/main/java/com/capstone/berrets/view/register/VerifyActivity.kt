package com.capstone.berrets.view.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityVerifyBinding

class VerifyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = resources.getColor(R.color.backgroundPrimary)
        window.navigationBarColor = resources.getColor(R.color.backgroundSecondary)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                )

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnVerify.setOnClickListener {
            val loginIntent = Intent(this, AccountDataActivity::class.java)
            startActivity(loginIntent)
        }
    }
}