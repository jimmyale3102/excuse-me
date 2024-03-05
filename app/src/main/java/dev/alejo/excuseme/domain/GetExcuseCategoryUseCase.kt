package dev.alejo.excuseme.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shuffle
import dev.alejo.excuseme.core.Constants.RANDOM_CATEGORY_ID
import dev.alejo.excuseme.data.local.ExcuseCategory
import dev.alejo.excuseme.data.repository.ExcuseRepository
import javax.inject.Inject

class GetExcuseCategoryUseCase @Inject constructor(private val repository: ExcuseRepository) {
    operator fun invoke(): List<ExcuseCategory> {
        val categories = mutableListOf<ExcuseCategory>(getRandomCategory())
        categories.addAll(repository.getCategories().sortedBy { it.name })
        return categories.toList()
    }

    private fun getRandomCategory(): ExcuseCategory =
        ExcuseCategory(id = RANDOM_CATEGORY_ID, name = "Random", icon = Icons.Default.Shuffle)
}