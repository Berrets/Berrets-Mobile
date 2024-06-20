package com.capstone.berrets.view.register

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityRegisterBinding
import com.capstone.berrets.factory.UserViewModelFactory
import com.capstone.berrets.view.login.LoginActivity
import com.capstone.berrets.view.main.MainActivity
import com.capstone.berrets.view.register.viewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

	private lateinit var binding: ActivityRegisterBinding
	private val viewModel by viewModels<RegisterViewModel> {
		UserViewModelFactory.getInstance(this)
	}

	@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityRegisterBinding.inflate(layoutInflater)
		setContentView(binding.root)

		setupActivity()
		setupObservers()
	}

	@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
	private fun setupActivity() {
		window.statusBarColor = resources.getColor(R.color.backgroundPrimary)
		window.navigationBarColor = resources.getColor(R.color.backgroundSecondary)

		window.decorView.systemUiVisibility = (
			View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
				or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
			)

		binding.btnBack.setOnClickListener {
			finish()
		}

		binding.btnRegister.setOnClickListener {
			val email = binding.etUsername.toString()
			viewModel.checkEmailIsRegistered(email)
		}

		binding.btnLoginGoogle.setOnClickListener {
			viewModel.loginWithGoogle(this)
		}

		binding.btnLogin.setOnClickListener {
			val loginIntent = Intent(this, LoginActivity::class.java)
			startActivity(loginIntent)
		}
	}

	private fun setupObservers() {
		viewModel.loginGoogleResponse.observe(this) { isSuccess ->
			if (isSuccess) {
				Toast.makeText(this, "Login With Google Success", Toast.LENGTH_SHORT).show()
				val intent = Intent(this, MainActivity::class.java)
				intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
				startActivity(intent)
			} else {
				Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
			}
		}

		viewModel.isEmailRegistered.observe(this) { isRegistered ->
			if (isRegistered) {
				binding.alertRegister.visibility = View.VISIBLE
			} else {
				val intent = Intent(this, AccountDataActivity::class.java)
				intent.putExtra("email", binding.etUsername.text.toString())
				startActivity(intent)
			}
		}
	}
}