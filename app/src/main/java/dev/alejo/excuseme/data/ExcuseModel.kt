package dev.alejo.excuseme.data

import com.google.gson.annotations.SerializedName

data class ExcuseModel(
    @SerializedName("id") val id: Int,
    @SerializedName("excuse") val excuse: String,
    @SerializedName("category") val category: String
)