package com.capstone.berrets.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityMainBinding
import com.capstone.berrets.view.qualityDetection.DetailDetectionActivity

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		window.navigationBarColor = resources.getColor(R.color.backgroundPrimary)

		window.decorView.systemUiVisibility = (
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
						or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
				)

		setupBottomNavbar()
	}

	private fun setupBottomNavbar() {
		val navView: BottomNavigationView = binding.navView
		val navController = findNavController(R.id.nav_host_fragment_activity_home)
		navView.setupWithNavController(navController)

		ViewCompat.setOnApplyWindowInsetsListener(navView) { view, insets ->
			val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
				bottomMargin = systemBarsInsets.bottom
			}
			insets
		}
	}
}