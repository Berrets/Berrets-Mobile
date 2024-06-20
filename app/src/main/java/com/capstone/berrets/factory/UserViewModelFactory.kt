package com.capstone.berrets.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.berrets.di.Injection
import com.capstone.berrets.local.repository.UserRepository
import com.capstone.berrets.view.login.LoginViewModel
import com.capstone.berrets.view.main.MainViewModel
import com.capstone.berrets.view.main.home.HomeViewModel
import com.capstone.berrets.view.register.viewModel.AccountDataViewModel
import com.capstone.berrets.view.register.viewModel.RegisterViewModel
import com.capstone.berrets.view.splash.SplashViewModel

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.NewInstanceFactory() {
	override fun <T: ViewModel> create(modelClass: Class<T>): T {
		return when {
			modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
				SplashViewModel(userRepository) as T
			}
			modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
				LoginViewModel(userRepository) as T
			}
			modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
				RegisterViewModel(userRepository) as T
			}
			modelClass.isAssignableFrom(AccountDataViewModel::class.java) -> {
				AccountDataViewModel(userRepository) as T
			}
			modelClass.isAssignableFrom(MainViewModel::class.java) -> {
				MainViewModel(userRepository) as T
			}
			modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
				HomeViewModel(userRepository) as T
			}
			else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
		}
	}

	companion object {
		fun getInstance(context: Context) = UserViewModelFactory(Injection.provideUserRepository(context))
	}
}