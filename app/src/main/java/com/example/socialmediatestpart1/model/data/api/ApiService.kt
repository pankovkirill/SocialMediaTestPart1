package com.example.socialmediatestpart1.model.data.api

import com.example.socialmediatestpart1.model.data.BestSellerModel
import com.example.socialmediatestpart1.model.data.CarouselModel
import com.example.socialmediatestpart1.model.data.SimilarModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {
    @GET("best")
    fun getBestAsync(): Deferred<List<BestSellerModel>>

    @GET("carousel")
    fun getCarouselDataAsync(): Deferred<List<CarouselModel>>

    @GET("similar")
    fun getSimilarAsync(): Deferred<List<SimilarModel>>
}