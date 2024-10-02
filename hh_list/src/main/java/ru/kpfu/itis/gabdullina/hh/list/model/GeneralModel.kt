package ru.kpfu.itis.gabdullina.hh.list.model

sealed class GeneralModel {
    data class ButtonModel(
        val cnt: Int
    ) : GeneralModel()
    data class VacancyModel(
        val lookingNumber: String,
        val name: String,
        val city: String,
        val company: String,
        val experience: String,
        val date: String,
        val isFavorite: Boolean
    ): GeneralModel()
}
