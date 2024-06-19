package com.capstone.berrets.view.onBoard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityOnboardBinding

class OnboardActivity : AppCompatActivity() {

	private lateinit var binding: ActivityOnboardBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityOnboardBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}