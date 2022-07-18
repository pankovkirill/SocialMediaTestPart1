package com.example.socialmediatestpart1.model.datasource

import com.example.socialmediatestpart1.model.data.BestSellerModel
import com.example.socialmediatestpart1.model.data.CarouselModel
import com.example.socialmediatestpart1.model.data.SimilarModel
import com.example.socialmediatestpart1.model.data.api.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl : DataSource {
    override suspend fun getBest(): List<BestSellerModel> {
        return getService().getBestAsync().await()
    }

    override suspend fun getSimilar(): List<SimilarModel> {
        return getService().getSimilarAsync().await()
    }

    override suspend fun getCarousel(): List<CarouselModel> {
        return getService().getCarouselDataAsync().await()
    }

    private fun getService(): ApiService {
        return createRetrofit().create(ApiService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createOkHttpClient())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        return httpClient.build()
    }

    companion object {
        private const val BASE_URL =
            "https://my-json-server.typicode.com/stellardiver/ebookdata/"
    }
}