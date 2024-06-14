package com.capstone.berrets.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.berrets.di.Injection
import com.capstone.berrets.local.repository.PostRepository
import com.capstone.berrets.view.main.community.CommunityViewModel

@Suppress("UNCHECKED_CAST")
class PostViewModelFactory(private val repository: PostRepository): ViewModelProvider.NewInstanceFactory() {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return when {
			modelClass.isAssignableFrom(CommunityViewModel::class.java) -> {
				CommunityViewModel(repository) as T
			}
			else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
		}
	}

	companion object {
		@Volatile
		private var INSTANCE: PostViewModelFactory? = null
		@JvmStatic
		fun getInstance(context: Context): PostViewModelFactory {
			if (INSTANCE == null) {
				synchronized(PostViewModelFactory::class.java) {
					INSTANCE = PostViewModelFactory(Injection.providePostRepository(context))
				}
			}
			return INSTANCE as PostViewModelFactory
		}
	}
}