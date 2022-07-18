package com.example.socialmediatestpart1.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarouselModel(
    val id: Int,
    val image: String
) : Parcelable