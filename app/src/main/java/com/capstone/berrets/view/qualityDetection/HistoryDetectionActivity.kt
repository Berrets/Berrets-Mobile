package com.capstone.berrets.view.qualityDetection

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.berrets.databinding.ActivityHistoryDetectionBinding
import com.capstone.berrets.factory.UserViewModelFactory
import com.capstone.berrets.view.qualityDetection.viewModel.HistoryDetectionViewModel

class HistoryDetectionActivity : AppCompatActivity() {

	private lateinit var binding: ActivityHistoryDetectionBinding
	private val viewModel by viewModels<HistoryDetectionViewModel> {
		UserViewModelFactory.getInstance(this)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityHistoryDetectionBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.btnBack.setOnClickListener {
			finish()
		}
	}
}