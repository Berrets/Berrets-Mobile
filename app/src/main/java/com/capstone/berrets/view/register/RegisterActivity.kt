package com.capstone.berrets.view.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.berrets.R
import com.capstone.berrets.api.response.ResultState
import com.capstone.berrets.databinding.ActivityRegisterBinding
import com.capstone.berrets.view.ViewModelFactory
import com.capstone.berrets.view.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

	private lateinit var binding: ActivityRegisterBinding

	private val viewModel by viewModels<RegisterViewModel> {
		ViewModelFactory.getInstance(this)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityRegisterBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}

	private fun setupAction() {
		binding.button4.setOnClickListener {
			val email = binding.emailEditText.text.toString()

			when {

				email.isEmpty() -> {
					binding.emailEditText.error = getString(R.string.email_error)
					binding.emailEditText.requestFocus()
					return@setOnClickListener
				}


				else -> register(email)
			}
		}
	}

	private fun register(email: String) {
		viewModel.register(email).observe(this) { result ->
			if (result != null) {
				when (result) {
					is ResultState.Loading -> {
						showLoading(true)
					}

					is ResultState.Success -> {
						showLoading(false)
						AlertDialog.Builder(this).apply {
							setTitle("Congrats!")
							setMessage(result.data.toString())
							setPositiveButton("Next") { _, _ ->
								val intent = Intent(context, LoginActivity::class.java)
								startActivity(intent)
								finish()
							}
							create()
							show()
						}
					}

					is ResultState.Error -> {
						Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
						showLoading(false)
					}
				}
			}
		}
	}

}