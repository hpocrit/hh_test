package ru.kpfu.itis.gabdullina.hh.list.ui.mapper

fun Int.toCntOfVacancies(): String {
    if (this % 10 == 1) {
        return "$this вакансия"
    } else if(this % 10 == 2 || this % 10 == 3 || this % 10 == 4) {
        return "$this вакансии"
    } else {
        return "$this вакансий"
    }
}
