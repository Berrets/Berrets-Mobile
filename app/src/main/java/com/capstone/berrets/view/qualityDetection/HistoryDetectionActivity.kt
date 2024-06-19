package com.capstone.berrets.view.qualityDetection

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityHistoryDetectionBinding
import com.capstone.berrets.databinding.ActivityRegisterBinding

class HistoryDetectionActivity : AppCompatActivity() {

	private lateinit var binding: ActivityHistoryDetectionBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityHistoryDetectionBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}