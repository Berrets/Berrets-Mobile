package com.capstone.berrets.view.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivitySplashBinding
import com.capstone.berrets.factory.UserViewModelFactory
import com.capstone.berrets.helper.getUsernameInEmail
import com.capstone.berrets.view.main.MainActivity
import com.capstone.berrets.view.main.MainViewModel
import com.capstone.berrets.view.onBoard.OnboardActivity

class SplashActivity : AppCompatActivity() {

	private lateinit var binding: ActivitySplashBinding
	private val viewModel by viewModels<SplashViewModel> {
		UserViewModelFactory.getInstance(this)
	}

	@RequiresApi(Build.VERSION_CODES.O)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
		binding = ActivitySplashBinding.inflate(layoutInflater)
		setContentView(binding.root)

		window.statusBarColor = resources.getColor(R.color.backgroundSecondary)
		window.navigationBarColor = resources.getColor(R.color.backgroundSecondary)

		window.decorView.systemUiVisibility = (
			View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
				or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
			)

		viewModel.authProvider.observe(this) { authProvider ->
			if (authProvider == "google") {
				val user = viewModel.getGoogleUser()
				val intent = Intent(this, MainActivity::class.java)
				val username = user?.email.toString().getUsernameInEmail()
				intent.putExtra("username", username)
				intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
				startActivity(intent)
			} else {
				val intent = Intent(this, OnboardActivity::class.java)
				startActivity(intent)
			}
		}
	}
}