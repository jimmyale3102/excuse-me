package dev.alejo.excuseme.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExcuseService @Inject constructor(private val apiClient: ExcuseApiClient) {

    suspend fun getRandomExcuse(): List<ExcuseModel>? = withContext(Dispatchers.IO) {
        try {
            apiClient.getRandomExcuse().body()
        } catch (e: Throwable) {
            null
        }
    }

}