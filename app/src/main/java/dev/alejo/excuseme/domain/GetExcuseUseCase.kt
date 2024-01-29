package dev.alejo.excuseme.domain

import javax.inject.Inject

class GetExcuseUseCase @Inject constructor() {
    suspend operator fun invoke(): String = "Heyyy this is my excuse bitch"
}