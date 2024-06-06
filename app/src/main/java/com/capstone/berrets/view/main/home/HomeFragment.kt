package com.capstone.berrets.view.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.capstone.berrets.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding
	private val viewModel: HomeViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		binding = FragmentHomeBinding.inflate(inflater, container, false)

		return binding.root
	}
}