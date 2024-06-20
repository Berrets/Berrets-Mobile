package com.capstone.berrets.view.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.capstone.berrets.R
import com.capstone.berrets.databinding.FragmentHomeBinding
import com.capstone.berrets.factory.UserViewModelFactory
import com.capstone.berrets.helper.TimeOfDay
import com.capstone.berrets.helper.getUsernameInEmail
import com.capstone.berrets.helper.timeOfDay
import com.capstone.berrets.view.main.MainViewModel
import com.capstone.berrets.view.qualityDetection.QualityDetectionActivity
import java.util.Date

class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding
	private val viewModel by viewModels<HomeViewModel> {
		UserViewModelFactory.getInstance(requireActivity())
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
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

		viewModel.getSession().observe(viewLifecycleOwner) { user ->
			val greeting = resources.getString(R.string.user_greeting)
			binding.textGreeting.text = String.format(greeting, day, user.fullname)
		}
	}

	private fun setupClickableView() {
		binding.recommendation.setOnClickListener {
			Toast.makeText(requireContext(), "Recommendation Coming Soon", Toast.LENGTH_SHORT).show()
		}

		binding.detection.setOnClickListener {
			startActivity(Intent(requireContext(), QualityDetectionActivity::class.java))
		}

		binding.pricing.setOnClickListener {
			Toast.makeText(requireContext(), "Rice Pricing Coming Soon", Toast.LENGTH_SHORT).show()
		}

		binding.others.setOnClickListener {
			Toast.makeText(requireContext(), "Coming Soon", Toast.LENGTH_SHORT).show()
		}
	}
}