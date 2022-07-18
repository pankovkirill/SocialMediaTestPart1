package com.example.socialmediatestpart1.model.datasource

import com.example.socialmediatestpart1.model.data.BestSellerModel
import com.example.socialmediatestpart1.model.data.CarouselModel
import com.example.socialmediatestpart1.model.data.SimilarModel

interface DataSource {
    suspend fun getBest(): List<BestSellerModel>
    suspend fun getSimilar(): List<SimilarModel>
    suspend fun getCarousel(): List<CarouselModel>
}