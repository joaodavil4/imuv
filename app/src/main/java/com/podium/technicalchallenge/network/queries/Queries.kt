package com.podium.technicalchallenge.network.queries

object Queries {
    fun getMoviesQuery() =
"""
    query GetMoviesQuery {
  movies {
    id
    title
    overview
    posterPath
    popularity
    releaseDate
    genres
  }
}
"""
}
