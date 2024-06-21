package com.capstone.berrets.view.main.profile

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.berrets.R
import com.capstone.berrets.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

	private lateinit var binding: FragmentProfileBinding
	private val viewModel: ProfileViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		binding = FragmentProfileBinding.inflate(inflater, container, false)

		viewModel.name.observe(viewLifecycleOwner) {
			binding.textUsername.text = it
		}

		return binding.root
	}
}