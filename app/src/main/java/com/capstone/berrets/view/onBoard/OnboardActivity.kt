package com.capstone.berrets.view.onBoard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityOnboardBinding
import com.capstone.berrets.view.login.LoginActivity
import com.capstone.berrets.view.register.RegisterActivity

class OnboardActivity : AppCompatActivity() {

	private lateinit var binding: ActivityOnboardBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityOnboardBinding.inflate(layoutInflater)
		setContentView(binding.root)

		window.navigationBarColor = resources.getColor(R.color.backgroundSecondary)

		window.decorView.systemUiVisibility = (
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
				)

		binding.btnRegister.setOnClickListener {
			val registerIntent = Intent(this, RegisterActivity::class.java)
			startActivity(registerIntent)
		}

		binding.btnLogin.setOnClickListener {
			val loginIntent = Intent(this, LoginActivity::class.java)
			startActivity(loginIntent)
		}
	}
}