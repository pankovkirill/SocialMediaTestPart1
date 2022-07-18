package com.example.socialmediatestpart1.model.data

sealed class AppState {
    data class SuccessBest(val data: List<BestSellerModel>?) : AppState()
    data class SuccessSimilar(val data: List<SimilarModel>?) : AppState()
    data class SuccessCarousel(val data: List<CarouselModel>?) : AppState()

    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}