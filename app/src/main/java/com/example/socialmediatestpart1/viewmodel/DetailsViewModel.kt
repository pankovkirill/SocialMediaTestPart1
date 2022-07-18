package com.example.socialmediatestpart1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediatestpart1.model.data.AppState
import com.example.socialmediatestpart1.model.repository.Repository
import com.example.socialmediatestpart1.model.repository.RepositoryImpl
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: Repository = RepositoryImpl()
) : ViewModel() {
    private val _liveData = MutableLiveData<AppState>()
    val liveData: LiveData<AppState> = _liveData

    fun getData() {
        _liveData.postValue(AppState.Loading)
        viewModelScope.launch { loadData() }
    }

    private suspend fun loadData() {
        try {
            _liveData.postValue(AppState.SuccessSimilar(repository.getSimilar()))
        } catch (e: Throwable) {
            _liveData.postValue(AppState.Error(e))
        }
    }
}