package com.capstone.berrets.view.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityRegisterBinding
import com.capstone.berrets.view.login.LoginActivity
import com.capstone.berrets.view.main.MainActivity

class RegisterActivity : AppCompatActivity() {

	private lateinit var binding: ActivityRegisterBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityRegisterBinding.inflate(layoutInflater)
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

		binding.btnRegister.setOnClickListener {
			val loginIntent = Intent(this, VerifyActivity::class.java)
			startActivity(loginIntent)
		}

		binding.btnLogin.setOnClickListener {
			val loginIntent = Intent(this, LoginActivity::class.java)
			startActivity(loginIntent)
		}

	}
}