package ru.kpfu.itis.gabdullina.hh.api.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("button")
    val button: Button?
)

@Serializable
data class Button(
    @SerializedName("text")
    val text: String?
)
