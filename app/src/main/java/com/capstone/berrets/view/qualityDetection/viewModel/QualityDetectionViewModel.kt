package com.capstone.berrets.view.qualityDetection.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.berrets.api.response.DetectionDataResponse
import com.capstone.berrets.local.repository.UserRepository
import kotlinx.coroutines.launch

class QualityDetectionViewModel(private val repository: UserRepository): ViewModel() {
	private val _uploadDetection = MutableLiveData<DetectionDataResponse>()
	val uploadDetection: MutableLiveData<DetectionDataResponse> = _uploadDetection

	private val _isLoading = MutableLiveData<Boolean>()
	val isLoading: LiveData<Boolean> = _isLoading

	fun uploadDetection(image: ByteArray, fileName: String) {
		_isLoading.value = true
		viewModelScope.launch {
			val result = repository.uploadDetection(image, fileName)
			_uploadDetection.value = result
			_isLoading.value = false
		}
	}
}