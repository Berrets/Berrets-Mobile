package com.capstone.berrets.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityLoginBinding
import com.capstone.berrets.view.main.MainActivity
import com.capstone.berrets.view.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

	private lateinit var binding: ActivityLoginBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityLoginBinding.inflate(layoutInflater)
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

		binding.btnLogin.setOnClickListener {
			val loginIntent = Intent(this, MainActivity::class.java)
			startActivity(loginIntent)
		}

		binding.btnRegister.setOnClickListener {
			val loginIntent = Intent(this, RegisterActivity::class.java)
			startActivity(loginIntent)
		}
	}
}