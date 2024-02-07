package dev.alejo.excuseme.data.repository

import dev.alejo.excuseme.data.ExcuseModel
import dev.alejo.excuseme.data.ExcuseService
import javax.inject.Inject

class ExcuseRepository @Inject constructor(private val service: ExcuseService) {
    suspend fun getExcuse(): ExcuseModel? = service.getRandomExcuse()?.get(0)
}