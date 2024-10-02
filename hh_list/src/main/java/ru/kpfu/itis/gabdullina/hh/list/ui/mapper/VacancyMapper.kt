package ru.kpfu.itis.gabdullina.hh.list.ui.mapper

import ru.kpfu.itis.gabdullina.hh.api.model.Vacancy
import ru.kpfu.itis.gabdullina.hh.list.model.GeneralModel
import ru.kpfu.itis.gabdullina.hh.list.model.VacancyItemModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


fun Vacancy.toItemModel(): VacancyItemModel {
    var lookingNumber: String
    var name: String
    var city: String = ""
    var company: String
    var experience: String = ""
    var date: String = ""

    val _lookingNumber = this.lookingNumber?:0

    if (_lookingNumber % 10 == 2 || _lookingNumber % 10 == 3 || _lookingNumber % 10 == 4) {
        lookingNumber = "Сейчас просматривает " + _lookingNumber + " человека"
    } else {
        lookingNumber = "Сейчас просматривает " + _lookingNumber + " человек"
    }

    name = this.title.toString()

    if(this.address != null) {
        city = this.address?.town.toString()
    }

    company = this.company.toString()

    val _experience = this.experience
    if(_experience != null) {
        experience = _experience.previewText.toString()
    }

    val _date = this.publishedDate
    if(_date != null) {
        date = formatDate(_date)
    }

    return VacancyItemModel(
        lookingNumber,
        name,
        city,
        company,
        experience,
        date
    )
}

fun Vacancy.toVacancyModel(): GeneralModel.VacancyModel {
    var lookingNumber: String
    var name: String
    var city: String = ""
    var company: String
    var experience: String = ""
    var date: String = ""
    val isFavorite = this.isFavorite?: false

    val _lookingNumber = this.lookingNumber?:0

    if (_lookingNumber % 10 == 2 || _lookingNumber % 10 == 3 || _lookingNumber % 10 == 4) {
        lookingNumber = "Сейчас просматривает " + _lookingNumber + " человека"
    } else {
        lookingNumber = "Сейчас просматривает " + _lookingNumber + " человек"
    }

    name = this.title.toString()

    if(this.address != null) {
        city = this.address?.town.toString()
    }

    company = this.company.toString()

    val _experience = this.experience
    if(_experience != null) {
        experience = _experience.previewText.toString()
    }

    val _date = this.publishedDate
    if(_date != null) {
        date = formatDate(_date)
    }

    return GeneralModel.VacancyModel(
        lookingNumber,
        name,
        city,
        company,
        experience,
        date,
        isFavorite
    )
}

fun formatDate(dateStr: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(dateStr)

    val calendar = Calendar.getInstance()
    calendar.time = date
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH) + 1

    val months = mapOf(
        1 to "января",
        2 to "февраля",
        3 to "марта",
        4 to "апреля",
        5 to "мая",
        6 to "июня",
        7 to "июля",
        8 to "августа",
        9 to "сентября",
        10 to "октября",
        11 to "ноября",
        12 to "декабря"
    )

    val monthName = months[month] ?: "месяца"

    return "Опубликовано $day $monthName"
}

