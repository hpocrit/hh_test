package ru.kpfu.itis.gabdullina.hh.list.domain.repository

import ru.kpfu.itis.gabdullina.hh.api.model.Response

interface VacancyRepository {
    suspend fun getAll(): Response
}
