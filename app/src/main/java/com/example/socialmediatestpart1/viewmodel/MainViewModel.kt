package com.example.socialmediatestpart1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediatestpart1.model.data.AppState
import com.example.socialmediatestpart1.model.repository.Repository
import com.example.socialmediatestpart1.model.repository.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val repository: Repository = RepositoryImpl()
) : ViewModel() {

    private val _carouselLiveData = MutableLiveData<AppState>()
    private val _bestLiveData = MutableLiveData<AppState>()

    val carouselLiveData: LiveData<AppState> = _carouselLiveData
    val bestLiveData: LiveData<AppState> = _bestLiveData

    fun getData() {
        _carouselLiveData.postValue(AppState.Loading)
        _bestLiveData.postValue(AppState.Loading)

        viewModelScope.launch {
            loadCarouselData()
            loadBestData()
        }
    }

    private suspend fun loadBestData() = withContext(Dispatchers.IO) {
        try {
            _bestLiveData.postValue(AppState.SuccessBest(repository.getBest()))
        } catch (e: Throwable) {
            _bestLiveData.postValue(AppState.Error(e))
        }
    }

    private suspend fun loadCarouselData() = withContext(Dispatchers.IO) {
        try {
            _carouselLiveData.postValue(AppState.SuccessCarousel(repository.getCarousel()))
        } catch (e: Throwable) {
            _carouselLiveData.postValue(AppState.Error(e))
        }
    }
}