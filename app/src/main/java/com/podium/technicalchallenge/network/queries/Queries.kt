package com.podium.technicalchallenge.network.queries

object Queries {
    fun getMovieQuery(id: String?) =
        """
    query GetMovieQuery {
  movie (id: $id){
    cast {
        name
    }
    director {
        name
    }
  }
}
"""

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

    fun getGenresQuery() =
        """
    query GetGenresQuery {
        genres
    }
"""
}
