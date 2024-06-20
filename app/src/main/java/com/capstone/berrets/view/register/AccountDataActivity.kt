package com.capstone.berrets.view.register

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityAccountDataBinding
import com.capstone.berrets.factory.UserViewModelFactory
import com.capstone.berrets.model.Register
import com.capstone.berrets.view.main.MainActivity
import com.capstone.berrets.view.register.viewModel.AccountDataViewModel

class AccountDataActivity : AppCompatActivity() {

	private lateinit var binding: ActivityAccountDataBinding
	private val viewModel by viewModels<AccountDataViewModel> {
		UserViewModelFactory.getInstance(this)
	}

	@RequiresApi(Build.VERSION_CODES.O)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityAccountDataBinding.inflate(layoutInflater)
		setContentView(binding.root)

		window.statusBarColor = resources.getColor(R.color.backgroundPrimary)
		window.navigationBarColor = resources.getColor(R.color.backgroundSecondary)

		window.decorView.systemUiVisibility = (
			View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
				or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
			)

		binding.btnBack.setOnClickListener {
			finish()
		}

		viewModel.registerResponse.observe(this) { response ->
			if (response.message == "Success") {
				Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
				val intent = Intent(this, MainActivity::class.java)
				intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
				startActivity(intent)
			} else {
				Toast.makeText(this, "Login Failed: ${response.message}", Toast.LENGTH_SHORT).show()
			}
		}

		var selectedRole = ""
		binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
			when (checkedId) {
				R.id.radio_traditional_market -> selectedRole = "Traditional Market"
				R.id.radio_modern_market -> selectedRole = "Modern Market"
				R.id.radio_big_market -> selectedRole = "Big Market"
				R.id.radio_manufacturer -> selectedRole = "Manufacturer"
			}
		}

		binding.btnRegister.setOnClickListener {
			val dataUser = Register(
				email = intent.getStringExtra("email").toString(),
				userName = binding.etUsername.text.toString(),
				fullName = binding.etFullname.text.toString(),
				password = binding.etPassword.text.toString(),
				roleUser = selectedRole
			)

			viewModel.register(dataUser)
		}
	}
}