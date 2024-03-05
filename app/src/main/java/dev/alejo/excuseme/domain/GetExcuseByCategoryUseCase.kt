package dev.alejo.excuseme.domain

import dev.alejo.excuseme.data.ExcuseModel
import dev.alejo.excuseme.data.repository.ExcuseRepository
import javax.inject.Inject

class GetExcuseByCategoryUseCase @Inject constructor(private val repository: ExcuseRepository) {
    suspend operator fun invoke(categoryName: String): ExcuseModel? =
        repository.getExcuseByCategory(categoryName.lowercase())
}