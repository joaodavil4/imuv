package com.podium.technicalchallenge.entity

data class MovieResponse(
    val data: Movies
)

data class Movies(
    val movies: List<MovieEntity>
)

data class MovieEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val popularity: Float,
    val genres: List<String>,
    val posterPath: String,
    val releaseDate: String,
    val cast: CastEntity,
    val director: DirectorEntity
)

data class CastEntity(
    val profilePath: String,
    val name: String,
    val character: String,
    val order: Int,
)

data class DirectorEntity(
    val id: Int,
    val name: String,
)
