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

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityRegisterBinding.inflate(layoutInflater)
		setContentView(binding.root)

		setupActivity()
		setupObservers()
	}

	private fun setupActivity() {
		viewModel.isLoading.observe(this) {
			showLoading(it)
		}

		binding.btnBack.setOnClickListener {
			finish()
		}

		binding.btnRegister.setOnClickListener {
			val email = binding.etUsername.text.toString()
			if (email.isNotEmpty()) {
				viewModel.checkEmailIsRegistered(email)
			} else {
				Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
			}
		}


		binding.btnLogin.setOnClickListener {
			val loginIntent = Intent(this, LoginActivity::class.java)
			startActivity(loginIntent)
		}
	}

	private fun setupObservers() {
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

	private fun showLoading(isLoading: Boolean) {
		if (isLoading) {
			binding.progressBar.visibility = View.VISIBLE
		} else {
			binding.progressBar.visibility = View.INVISIBLE
		}
	}
}