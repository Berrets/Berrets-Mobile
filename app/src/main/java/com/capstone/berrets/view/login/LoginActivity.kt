package com.capstone.berrets.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.capstone.berrets.R
import com.capstone.berrets.api.response.ResultState
import com.capstone.berrets.databinding.ActivityLoginBinding
import com.capstone.berrets.model.User
import com.capstone.berrets.view.ViewModelFactory
import com.capstone.berrets.view.main.MainActivity

class LoginActivity : AppCompatActivity() {
	private lateinit var binding: ActivityLoginBinding

	private val viewModel by viewModels<LoginViewModel> {
		ViewModelFactory.getInstance(this)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityLoginBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}

	private fun setupAction() {
		binding.button4.setOnClickListener {
			val email = binding.emailEditText.text.toString()
			val password = binding.passwordEditText.text.toString()

			when {
				email.isEmpty() -> {
					binding.emailEditText.error = getString(R.string.email_error)
					binding.emailEditText.requestFocus()
					return@setOnClickListener
				}

				password.isEmpty() -> {
					binding.passwordEditText.error = getString(R.string.password_error)
					binding.passwordEditText.requestFocus()
					return@setOnClickListener
				}

				else -> login(email, password)
			}
		}
	}

	private fun login(email: String, password: String) {
		viewModel.login(email, password).observe(this) { result ->
			if (result != null) {
				when (result) {
					is ResultState.Loading -> {
						showLoading(true)
					}

					is ResultState.Success -> {
						viewModel.saveSessionToken(User(email, result.data.loginResult.token))
						showLoading(false)
						AlertDialog.Builder(this).apply {
							setTitle(getString(R.string.success))
							setMessage(result.data.message)
							setPositiveButton(getString(R.string.next)) { _, _ ->
								val intent = Intent(context, MainActivity::class.java)
								intent.flags =
									Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
								intent.putExtra(EXTRA_TOKEN, result.data.loginResult.token)
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

	private fun showLoading(isLoading: Boolean) {
		if (isLoading) binding.progressBar.visibility =
			View.VISIBLE else binding.progressBar.visibility = View.GONE
	}
}