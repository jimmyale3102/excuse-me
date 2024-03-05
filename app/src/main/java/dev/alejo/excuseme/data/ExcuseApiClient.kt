package dev.alejo.excuseme.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExcuseApiClient {

    @GET("excuse")
    suspend fun getRandomExcuse(): Response<List<ExcuseModel>>

    @GET("excuse/{${ApiPath.CATEGORY_NAME_PATH}}")
    suspend fun getExcuseByCategory(@Path(value = ApiPath.CATEGORY_NAME_PATH) categoryName: String)
        : Response<List<ExcuseModel>>


    private object ApiPath {
        const val CATEGORY_NAME_PATH = "categoryName"
    }
}