package ru.kpfu.itis.gabdullina.hh.favourites.data.repository

import ru.kpfu.itis.gabdullina.hh.api.VacancyService
import ru.kpfu.itis.gabdullina.hh.api.model.Response
import ru.kpfu.itis.gabdullina.hh.favourites.domain.repository.VacancyRepository
import javax.inject.Inject

class VacancyRepositoryImpl @Inject constructor(
    private val vacancyService: VacancyService
): VacancyRepository {
    override suspend fun getAll(): Response {
        return vacancyService.getAll()
    }
}
