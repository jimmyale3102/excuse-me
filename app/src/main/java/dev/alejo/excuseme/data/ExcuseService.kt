package dev.alejo.excuseme.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExcuseService @Inject constructor(private val apiClient: ExcuseApiClient) {

    suspend fun getRandomExcuse(): List<ExcuseModel>? = withContext(Dispatchers.IO) {
        try {
            apiClient.getRandomExcuse().body()
        } catch (e: Throwable) {
            printError(e)
            null
        }
    }

    suspend fun getExcuseByCategory(categoryName: String): List<ExcuseModel>? = withContext(Dispatchers.IO) {
        try {
            apiClient.getExcuseByCategory(categoryName).body()
        } catch (e: Throwable) {
            printError(e)
            null
        }
    }

    private fun printError(e: Throwable) {
        e.printStackTrace()
        Log.e(DebugTag.ERROR, e.message.toString())
    }

    private object DebugTag {
        const val ERROR = "ERROR_SERVICE"
    }

}