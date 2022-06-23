package com.podium.technicalchallenge.network.queries

object Queries {
    fun getMoviesQuery(filter: String?) =
"""
    query GetMoviesQuery {
  movies (${filter?:""}){
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
