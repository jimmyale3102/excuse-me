package dev.alejo.excuseme.data

import retrofit2.Response
import retrofit2.http.GET

interface ExcuseApiClient {

    @GET("excuse")
    suspend fun getRandomExcuse(): Response<List<ExcuseModel>>

}