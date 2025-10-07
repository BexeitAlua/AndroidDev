package com.example.assignment2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class NewsData(
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    var liked: Boolean = false
) : Parcelable
