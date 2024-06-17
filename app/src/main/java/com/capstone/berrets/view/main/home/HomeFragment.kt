package com.capstone.berrets.view.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.capstone.berrets.R
import com.capstone.berrets.databinding.FragmentHomeBinding
import com.capstone.berrets.helper.TimeOfDay
import com.capstone.berrets.helper.timeOfDay
import com.capstone.berrets.view.qualityDetection.QualityDetectionActivity
import java.util.Date

class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding
	private val viewModel: HomeViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		/*
		 * TODO: Get user Session from viewModel
		 */
		setupTimeOfDay()
		setupClickableView()
	}

	private fun setupTimeOfDay() {
		val currentTimeOfDay = Date().timeOfDay()
		val day: String
		when (currentTimeOfDay) {
			TimeOfDay.MORNING -> {
				binding.heroSceneryTime.setImageResource(R.drawable.hero_scenery_morning)
				day = "Pagi"
			}
			TimeOfDay.AFTERNOON -> {
				binding.heroSceneryTime.setImageResource(R.drawable.hero_scenery_afternoon)
				day = "Siang"
			}
			TimeOfDay.NIGHT -> {
				binding.heroSceneryTime.setImageResource(R.drawable.hero_scenery_night)
				day = "Malam"
			}
		}

		val greeting = getString(R.string.user_greeting)
		binding.textGreeting.text = String.format(greeting, day, "Berrets")
	}

	private fun setupClickableView() {
		binding.clickableRecommendation.apply {
			itemImage.setImageResource(R.drawable.hero_recommendation)
			itemLabel.text = getString(R.string.label_recommendation)
			item.setOnClickListener {
				Toast.makeText(requireContext(), "Coming Soon", Toast.LENGTH_SHORT).show()
			}
		}

		binding.clickableDetection.apply {
			itemImage.setImageResource(R.drawable.hero_detection)
			itemLabel.text = getString(R.string.label_detection)
			item.setOnClickListener {
				startActivity(Intent(requireContext(), QualityDetectionActivity::class.java))
			}
		}

		binding.clickablePricing.apply {
			itemImage.setImageResource(R.drawable.hero_pricing)
			itemLabel.text = getString(R.string.label_pricing)
			item.setOnClickListener {
				Toast.makeText(requireContext(), "Coming Soon", Toast.LENGTH_SHORT).show()
			}
		}

		binding.clickableOthers.apply {
			itemImage.setImageResource(R.drawable.hero_others)
			itemLabel.text = getString(R.string.label_others)
			item.setOnClickListener {
				Toast.makeText(requireContext(), "Coming Soon", Toast.LENGTH_SHORT).show()
			}
		}
	}
}