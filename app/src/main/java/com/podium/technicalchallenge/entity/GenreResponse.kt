package com.podium.technicalchallenge.entity

data class GenreResponse(
    val data: Genres
)

data class Genres(
    val genres: List<String>
)