package com.capstone.berrets.view.qualityDetection.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.berrets.api.response.DetectionDataResponse
import com.capstone.berrets.local.repository.UserRepository
import kotlinx.coroutines.launch

class HistoryDetectionViewModel(private val repository: UserRepository): ViewModel() {

}