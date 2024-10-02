package ru.kpfu.itis.gabdullina.hh.api.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("vacancies")
    val vacancies: List<Vacancy>,
    @SerializedName("offers")
    val offers: List<Offer>

)
