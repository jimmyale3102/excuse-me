package dev.alejo.excuseme.data.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.ChildFriendly
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Desk
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.PsychologyAlt
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material.icons.filled.VideogameAsset
import dev.alejo.excuseme.data.ExcuseModel
import dev.alejo.excuseme.data.ExcuseService
import dev.alejo.excuseme.data.local.ExcuseCategory
import javax.inject.Inject

class ExcuseRepository @Inject constructor(private val service: ExcuseService) {
    suspend fun getExcuse(): ExcuseModel? = service.getRandomExcuse()?.firstOrNull()

    suspend fun getExcuseByCategory(categoryName: String): ExcuseModel? =
        service.getExcuseByCategory(categoryName)?.firstOrNull()

    fun getCategories(): List<ExcuseCategory> = listOf(
        ExcuseCategory(name = "Family", icon = Icons.Default.FamilyRestroom),
        ExcuseCategory(name = "Office", icon = Icons.Default.Desk),
        ExcuseCategory(name = "Children", icon = Icons.Default.ChildFriendly),
        ExcuseCategory(name = "College", icon = Icons.Default.School),
        ExcuseCategory(name = "Party", icon = Icons.Default.Celebration),
        ExcuseCategory(name = "Funny", icon = Icons.Default.SentimentVerySatisfied),
        ExcuseCategory(name = "Unbelievable", icon = Icons.Default.PsychologyAlt),
        ExcuseCategory(name = "Developers", icon = Icons.Default.Code),
        ExcuseCategory(name = "Gaming", icon = Icons.Default.VideogameAsset)
    )
}