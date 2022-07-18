package com.example.socialmediatestpart1.model.data

import android.os.Parcelable
import android.provider.Telephony
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BestSellerModel(
    val id: Int,
    val title: String,
    val author: String,
    val price: Double,
    val image: String,
    val rate: Rate
) : Parcelable {
    @Parcelize
    data class Rate(
        val score: Double,
        val amount: Int
    ) : Parcelable
}