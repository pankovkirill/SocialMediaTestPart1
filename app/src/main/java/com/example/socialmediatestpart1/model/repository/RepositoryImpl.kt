package com.example.socialmediatestpart1.model.repository

import com.example.socialmediatestpart1.model.data.BestSellerModel
import com.example.socialmediatestpart1.model.data.CarouselModel
import com.example.socialmediatestpart1.model.data.SimilarModel
import com.example.socialmediatestpart1.model.datasource.DataSource
import com.example.socialmediatestpart1.model.datasource.RetrofitImpl

class RepositoryImpl(
    private val dataSource: DataSource = RetrofitImpl()
) : Repository {
    override suspend fun getBest(): List<BestSellerModel> {
        return dataSource.getBest()
    }

    override suspend fun getSimilar(): List<SimilarModel> {
        return dataSource.getSimilar()
    }

    override suspend fun getCarousel(): List<CarouselModel> {
        return dataSource.getCarousel()
    }
}