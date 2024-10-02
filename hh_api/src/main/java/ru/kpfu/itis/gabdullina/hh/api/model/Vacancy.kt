package ru.kpfu.itis.gabdullina.hh.api.model

import com.google.gson.annotations.SerializedName

data class Vacancy(
    @SerializedName("address")
    val address: Address?,
    @SerializedName("appliedNumber")
    val appliedNumber: Int?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("experience")
    val experience: Experience?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isFavorite")
    val isFavorite: Boolean?,
    @SerializedName("lookingNumber")
    val lookingNumber: Int?,
    @SerializedName("publishedDate")
    val publishedDate: String?,
    @SerializedName("questions")
    val questions: List<String>?,
    @SerializedName("responsibilities")
    val responsibilities: String?,
    @SerializedName("salary")
    val salary: Salary?,
    @SerializedName("schedules")
    val schedules: List<String>?,
    @SerializedName("title")
    val title: String?
)

data class Salary(
    @SerializedName("full")
    val full: String?,
    @SerializedName("short")
    val short: String?
)

data class Experience(
    @SerializedName("previewText")
    val previewText: String?,
    @SerializedName("text")
    val text: String?
)

data class Address(
    @SerializedName("house")
    val house: String?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("town")
    val town: String?
)

