package com.capstone.berrets.view.main.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.berrets.R
import com.capstone.berrets.databinding.FragmentCommunityBinding
import com.capstone.berrets.factory.PostViewModelFactory

class CommunityFragment : Fragment() {

	private lateinit var binding: FragmentCommunityBinding
	private val viewModel by viewModels<CommunityViewModel> {
		PostViewModelFactory.getInstance(requireContext())
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentCommunityBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		setupToolbar()
		populatePosts()
	}

	private fun populatePosts() {
		val layoutManager = LinearLayoutManager(requireContext())
		binding.rvPost.layoutManager = layoutManager

		viewModel.getPosts()

		viewModel.isLoading.observe(viewLifecycleOwner) {
			showLoading(it)
		}

		viewModel.posts.observe(viewLifecycleOwner) { posts ->
			val adapter = CommunityAdapter()
			adapter.submitList(posts)
			binding.rvPost.adapter = adapter
		}
	}

	private fun setupToolbar() {
		@Suppress("DEPRECATION")
		val statusBarHeight = view?.rootWindowInsets?.systemWindowInsetTop
		if (statusBarHeight != null) {
			binding.statusBarHeight.layoutParams.height = statusBarHeight
		}
		binding.toolbar.apply {
			inflateMenu(R.menu.community_toolbar)
			setOnMenuItemClickListener {
				when (it.itemId) {
					R.id.action_notification -> {
						// TODO: Navigate to notification screen
						true
					}

					R.id.action_search -> {
						// TODO: Navigate to search screen
						true
					}

					else -> {
						false
					}
				}
			}
		}
	}

	private fun showLoading(state: Boolean) {
		binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
	}

	companion object {
		private const val TAG = "CommunityFragment"
	}
}