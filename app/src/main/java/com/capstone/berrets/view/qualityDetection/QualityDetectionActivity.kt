package com.capstone.berrets.view.qualityDetection

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityQualityDetectionBinding
import com.capstone.berrets.helper.dp
import com.capstone.berrets.view.qualityDetection.custom.roundedShape
import com.capstone.berrets.view.register.VerifyActivity

class QualityDetectionActivity : AppCompatActivity() {

	private lateinit var binding: ActivityQualityDetectionBinding
	private val handler = Handler()
	private var scrollAmount = 2 // Adjust the scroll amount as needed
	private lateinit var scrollRunnable: Runnable
	private var scrollDirectionPrimary = SCROLL_RIGHT
	private var scrollDirectionSecondary = SCROLL_LEFT

	companion object {
		private const val SCROLL_RIGHT = 1
		private const val SCROLL_LEFT = -1
	}

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
		startAutoScroll()
	}

	private fun setupActivity() {
		binding.btnClose.setOnClickListener {
			onBackPressed()
		}

		binding.btnHistory.setOnClickListener {
			val historyIntent = Intent(this, HistoryDetectionActivity::class.java)
			startActivity(historyIntent)
		}

		createQualityCards()
		createRiceParameterCards()
	}

	private fun startAutoScroll() {
		scrollRunnable = object : Runnable {
			override fun run() {
				val maxScrollXPrimary = binding.riceParameterType.getChildAt(0).width - binding.riceParameterType.width
				val maxScrollXSecondary = binding.riceParameterTypeUnder.getChildAt(0).width - binding.riceParameterTypeUnder.width

				if (scrollDirectionPrimary == SCROLL_RIGHT) {
					binding.riceParameterType.scrollBy(scrollAmount, 0)
					if (binding.riceParameterType.scrollX >= maxScrollXPrimary) {
						scrollDirectionPrimary = SCROLL_LEFT
					}
				} else {
					binding.riceParameterType.scrollBy(-scrollAmount, 0)
					if (binding.riceParameterType.scrollX <= 0) {
						scrollDirectionPrimary = SCROLL_RIGHT
					}
				}

				if (scrollDirectionSecondary == SCROLL_LEFT) {
					binding.riceParameterTypeUnder.scrollBy(-scrollAmount, 0)
					if (binding.riceParameterTypeUnder.scrollX <= 0) {
						scrollDirectionSecondary = SCROLL_RIGHT
					}
				} else {
					binding.riceParameterTypeUnder.scrollBy(scrollAmount, 0)
					if (binding.riceParameterTypeUnder.scrollX >= maxScrollXSecondary) {
						scrollDirectionSecondary = SCROLL_LEFT
					}
				}

				handler.postDelayed(this, 60)
			}
		}
		handler.post(scrollRunnable)
	}

	override fun onDestroy() {
		super.onDestroy()
		handler.removeCallbacks(scrollRunnable)
	}

	private fun createQualityCards() {
		binding.premiumQualityType.apply {
			labelQuality.text = getString(R.string.premium)
			icon.backgroundTintList = getColorStateList(R.color.premium_bg)
			icon.imageTintList = ContextCompat.getColorStateList(this@QualityDetectionActivity, R.color.premium_text)
		}

		binding.mediumIQualityType.apply {
			labelQuality.text = getString(R.string.medium_i)
			icon.backgroundTintList = getColorStateList(R.color.medium_i_bg)
			icon.imageTintList = ContextCompat.getColorStateList(this@QualityDetectionActivity, R.color.medium_i_text)
		}

		binding.mediumIiQualityType.apply {
			labelQuality.text = getString(R.string.medium_ii)
			icon.backgroundTintList = getColorStateList(R.color.medium_ii_bg)
			icon.imageTintList = ContextCompat.getColorStateList(this@QualityDetectionActivity, R.color.medium_ii_text)
		}

		binding.lowerQualityType.apply {
			labelQuality.text = getString(R.string.lower)
			icon.backgroundTintList = getColorStateList(R.color.bottom_bg)
			icon.imageTintList = ContextCompat.getColorStateList(this@QualityDetectionActivity, R.color.bottom_text)
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