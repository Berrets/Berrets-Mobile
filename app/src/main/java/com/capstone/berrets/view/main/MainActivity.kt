package com.capstone.berrets.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityMainBinding
import com.capstone.berrets.view.ViewModelFactory
import com.capstone.berrets.view.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	private val viewModel by viewModels<MainViewModel> {
		ViewModelFactory.getInstance(this)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		installSplashScreen()
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		loginCheck()
	}

	private fun loginCheck() {
		viewModel.getSession().observe(this) { user ->
			if (!user.isLogin) {
				startActivity(Intent(this, WelcomeActivity::class.java))
				finish()
			}
		}

	}

	private fun showLoading(isLoading: Boolean) {
		if (isLoading) binding.progressBar.visibility =
			View.VISIBLE else binding.progressBar.visibility = View.GONE
	}
}