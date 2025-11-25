package com.example.assignment4.data.database.entity

import com.example.assignment4.data.api.Movie

fun Movie.toEntity(): MovieEntity = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath
)

fun MovieEntity.toMovie(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath
)