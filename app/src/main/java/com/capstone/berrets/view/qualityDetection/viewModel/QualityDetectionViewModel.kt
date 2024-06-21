package com.capstone.berrets.view.qualityDetection.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.berrets.api.response.DetectionDataResponse
import com.capstone.berrets.local.repository.UserRepository
import com.capstone.berrets.model.User
import kotlinx.coroutines.launch

class QualityDetectionViewModel(private val repository: UserRepository): ViewModel() {
	private val _uploadDetection = MutableLiveData<DetectionDataResponse>()
	val uploadDetection: MutableLiveData<DetectionDataResponse> = _uploadDetection

	private val _isLoading = MutableLiveData<Boolean>()
	val isLoading: LiveData<Boolean> = _isLoading

	fun uploadDetection(fileName: String, image: String) {
		_isLoading.value = true
		viewModelScope.launch {
			val result = repository.uploadDetection(fileName, image)
			_uploadDetection.value = result
			_isLoading.value = false
		}
	}


}