package com.ahmed.orangechallenge.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.domain.model.photo.Images
import com.ahmed.domain.model.photo.Photos
import com.ahmed.domain.use_case.photos.PhotosUseCase
import com.ahmed.domain.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val photosUseCase: PhotosUseCase
) : ViewModel() {

    private var _photosResponse =
        MutableStateFlow<DataState<Images<Photos>>>(DataState.Idle)
    val photos = _photosResponse.asStateFlow()

    fun getPhotos(title: String) {

        _photosResponse.value = DataState.Idle
        viewModelScope.launch {
            photosUseCase(title).collect {
                _photosResponse.value = it
            }
        }
    }
}