package com.podium.technicalchallenge.network.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GraphQLService {
    @Headers("Content-Type: application/json")
    @POST("graphql")
    suspend fun postGetMovies(@Body body: String): Response<String>

    @Headers("Content-Type: application/json")
    @POST("graphql")
    suspend fun postGetGenres(@Body body: String): Response<String>
}
