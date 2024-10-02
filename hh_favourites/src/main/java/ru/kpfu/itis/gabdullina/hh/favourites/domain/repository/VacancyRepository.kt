package ru.kpfu.itis.gabdullina.hh.favourites.domain.repository

import ru.kpfu.itis.gabdullina.hh.api.model.Response

interface VacancyRepository {
    suspend fun getAll(): Response
}
