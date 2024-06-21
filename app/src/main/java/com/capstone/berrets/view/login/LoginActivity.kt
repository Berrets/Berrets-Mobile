package com.capstone.berrets.view.login

import android.content.Intent
import android.credentials.GetCredentialException
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.lifecycleScope
import com.capstone.berrets.BuildConfig
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityLoginBinding
import com.capstone.berrets.factory.UserViewModelFactory
import com.capstone.berrets.view.main.MainActivity
import com.capstone.berrets.view.register.AccountDataActivity
import com.capstone.berrets.view.register.RegisterActivity
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

	private lateinit var binding: ActivityLoginBinding
	private val viewModel by viewModels<LoginViewModel> {
		UserViewModelFactory.getInstance(this)
	}

	@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityLoginBinding.inflate(layoutInflater)
		setContentView(binding.root)


		setupActivity()
	}

	@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
	private fun setupActivity() {
		window.statusBarColor = resources.getColor(R.color.backgroundPrimary)
		window.navigationBarColor = resources.getColor(R.color.backgroundSecondary)

		window.decorView.systemUiVisibility = (
			View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
				or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
			)

		viewModel.loginResponse.observe(this) { response ->
			if (response.jwtToken != "") {
				Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
				val intent = Intent(this, MainActivity::class.java)
				intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
				startActivity(intent)
			} else {
				Toast.makeText(this, "Login Failed: ${response.tokenObject}", Toast.LENGTH_SHORT).show()
			}
		}

		binding.btnBack.setOnClickListener {
			finish()
		}

		binding.btnLogin.setOnClickListener {
			val email = binding.etEmail.text.toString()
			val password = binding.etPassword.text.toString()

			viewModel.login(email, password)
		}

		binding.btnLoginGoogle.setOnClickListener {

		}

		binding.btnRegister.setOnClickListener {
			val loginIntent = Intent(this, RegisterActivity::class.java)
			startActivity(loginIntent)
		}
	}

	companion object {
		private const val TAG = "LoginActivity"
	}
}
