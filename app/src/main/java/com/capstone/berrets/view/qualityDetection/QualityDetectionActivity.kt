package com.capstone.berrets.view.qualityDetection

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityQualityDetectionBinding
import com.capstone.berrets.helper.dp
import com.capstone.berrets.view.qualityDetection.custom.roundedShape

class QualityDetectionActivity : AppCompatActivity() {

	private lateinit var binding: ActivityQualityDetectionBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		binding = ActivityQualityDetectionBinding.inflate(layoutInflater)
		setContentView(binding.root)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(
				systemBars.left,
				systemBars.top,
				systemBars.right,
				systemBars.bottom
			)
			insets
		}
		setupActivity()
	}

	private fun setupActivity() {
		binding.btnBack.setOnClickListener {
			finish()
		}
		createQualityCards()
		createRiceParameterCards()
	}

	private fun createQualityCards() {
		binding.premiumQualityType.apply {
			labelQuality.text = getString(R.string.premium)
			icon.backgroundTintList = getColorStateList(R.color.premium_bg)
		}

		binding.mediumIQualityType.apply {
			labelQuality.text = getString(R.string.medium_I)
			icon.backgroundTintList = getColorStateList(R.color.medium_i_bg)
		}

		binding.mediumIIQualityType.apply {
			labelQuality.text = getString(R.string.medium_II)
			icon.backgroundTintList = getColorStateList(R.color.medium_ii_bg)
		}

		binding.lowerQualityType.apply {
			labelQuality.text = getString(R.string.lower)
			icon.backgroundTintList = getColorStateList(R.color.bottom_bg)
		}
	}

	private fun createRiceParameterCards() {
		val context = binding.root.context

		binding.butirKapur.apply {
			val backgroundDrawable = roundedShape(
				backgroundColor = ContextCompat.getColor(context, R.color.butir_kapur_bg),
				strokeColor = ContextCompat.getColor(context, R.color.butir_kapur_outline),
				cornerRadius = 12f.dp(context),
				strokeWidth = 2.dp(context)
			)
			layout.background = backgroundDrawable
			riceType.text = getString(R.string.butir_kapur)
			riceDescription.text = getString(R.string.description_butir_kapur)
		}

		binding.butirKepala.apply {
			val backgroundDrawable = roundedShape(
				backgroundColor = ContextCompat.getColor(context, R.color.butir_kepala_bg),
				strokeColor = ContextCompat.getColor(context, R.color.butir_kepala_outline),
				cornerRadius = 12f.dp(context),
				strokeWidth = 2.dp(context)
			)
			layout.background = backgroundDrawable
			riceType.text = getString(R.string.butir_kepala)
			riceDescription.text = getString(R.string.description_butir_kepala)
		}

		binding.butirPatah.apply {
			val backgroundDrawable = roundedShape(
				backgroundColor = ContextCompat.getColor(context, R.color.butir_patah_bg),
				strokeColor = ContextCompat.getColor(context, R.color.butir_patah_outline),
				cornerRadius = 12f.dp(context),
				strokeWidth = 2.dp(context)
			)
			layout.background = backgroundDrawable
			riceType.text = getString(R.string.butir_patah)
			riceDescription.text = getString(R.string.description_butir_patah)
		}

		binding.butirMenir.apply {
			val backgroundDrawable = roundedShape(
				backgroundColor = ContextCompat.getColor(context, R.color.butir_menir_bg),
				strokeColor = ContextCompat.getColor(context, R.color.butir_menir_outline),
				cornerRadius = 12f.dp(context),
				strokeWidth = 2.dp(context)
			)
			layout.background = backgroundDrawable
			riceType.text = getString(R.string.butir_menir)
			riceDescription.text = getString(R.string.description_butir_menir)
		}

		binding.butirRusak.apply {
			val backgroundDrawable = roundedShape(
				backgroundColor = ContextCompat.getColor(context, R.color.butir_rusak_bg),
				strokeColor = ContextCompat.getColor(context, R.color.butir_rusak_outline),
				cornerRadius = 12f.dp(context),
				strokeWidth = 2.dp(context)
			)
			layout.background = backgroundDrawable
			riceType.text = getString(R.string.butir_rusak)
			riceDescription.text = getString(R.string.description_butir_rusak)
		}

		binding.kutu.apply {
			val backgroundDrawable = roundedShape(
				backgroundColor = ContextCompat.getColor(context, R.color.kutu_bg),
				strokeColor = ContextCompat.getColor(context, R.color.kutu_outline),
				cornerRadius = 12f.dp(context),
				strokeWidth = 2.dp(context)
			)
			layout.background = backgroundDrawable
			riceType.text = getString(R.string.kutu)
			riceDescription.text = getString(R.string.description_kutu)
		}

		binding.batu.apply {
			val backgroundDrawable = roundedShape(
				backgroundColor = ContextCompat.getColor(context, R.color.batu_bg),
				strokeColor = ContextCompat.getColor(context, R.color.batu_outline),
				cornerRadius = 12f.dp(context),
				strokeWidth = 2.dp(context)
				)
			layout.background = backgroundDrawable
			riceType.text = getString(R.string.batu)
			riceDescription.text = getString(R.string.description_batu)
			riceDescription.text = getString(R.string.description_batu)
		}

		binding.sekam.apply {
			val backgroundDrawable = roundedShape(
				backgroundColor = ContextCompat.getColor(context, R.color.sekam_bg),
				strokeColor = ContextCompat.getColor(context, R.color.sekam_outline),
				cornerRadius = 12f.dp(context),
				strokeWidth = 2.dp(context)
				)
			layout.background = backgroundDrawable
			riceType.text = getString(R.string.sekam)
			riceDescription.text = getString(R.string.description_sekam)
		}

		binding.butirMerah.apply {
			val backgroundDrawable = roundedShape(
				backgroundColor = ContextCompat.getColor(context, R.color.butir_merah_bg),
				strokeColor = ContextCompat.getColor(context, R.color.butir_merah_outline),
				cornerRadius = 12f.dp(context),
				strokeWidth = 2.dp(context)
				)
			layout.background = backgroundDrawable
			riceType.text = getString(R.string.butir_merah)
			riceDescription.text = getString(R.string.description_butir_merah)
		}

		binding.butirGabah.apply {
			val backgroundDrawable = roundedShape(
				backgroundColor = ContextCompat.getColor(context, R.color.butir_gabah_bg),
				strokeColor = ContextCompat.getColor(context, R.color.butir_gabah_outline),
				cornerRadius = 12f.dp(context),
				strokeWidth = 2.dp(context)
				)
			layout.background = backgroundDrawable
			riceType.text = getString(R.string.butir_gabah)
			riceDescription.text = getString(R.string.description_butir_gabah)
		}
	}
}