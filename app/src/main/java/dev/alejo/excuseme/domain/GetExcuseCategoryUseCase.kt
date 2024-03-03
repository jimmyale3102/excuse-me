package dev.alejo.excuseme.domain

import dev.alejo.excuseme.data.local.ExcuseCategory
import dev.alejo.excuseme.data.repository.ExcuseRepository
import javax.inject.Inject

class GetExcuseCategoryUseCase @Inject constructor(private val repository: ExcuseRepository) {
    operator fun invoke(): List<ExcuseCategory> = repository.getCategories().sortedBy { it.name }
}