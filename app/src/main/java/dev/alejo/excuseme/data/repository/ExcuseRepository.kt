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
    suspend fun getExcuse(): ExcuseModel? = service.getRandomExcuse()?.get(0)
    fun getCategories(): List<ExcuseCategory> = listOf(
        ExcuseCategory("Family", Icons.Default.FamilyRestroom),
        ExcuseCategory("Office", Icons.Default.Desk),
        ExcuseCategory("Children", Icons.Default.ChildFriendly),
        ExcuseCategory("College", Icons.Default.School),
        ExcuseCategory("Party", Icons.Default.Celebration),
        ExcuseCategory("Funny", Icons.Default.SentimentVerySatisfied),
        ExcuseCategory("Unbelievable", Icons.Default.PsychologyAlt),
        ExcuseCategory("Developers", Icons.Default.Code),
        ExcuseCategory("Gaming", Icons.Default.VideogameAsset)
    )
}