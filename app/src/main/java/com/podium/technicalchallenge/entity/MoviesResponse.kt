package com.podium.technicalchallenge.entity

data class MoviesResponse(
    val data: Movies
)

data class MovieResponse(
    val data: Movie
)

data class Movie(
    val movie: MovieEntity
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
    var cast: List<CastEntity>,
    var director: DirectorEntity
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
