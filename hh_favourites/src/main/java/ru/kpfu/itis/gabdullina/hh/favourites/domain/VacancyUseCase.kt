package ru.kpfu.itis.gabdullina.hh.favourites.domain

import kotlinx.coroutines.CancellationException
import ru.kpfu.itis.gabdullina.hh.api.model.Vacancy
import ru.kpfu.itis.gabdullina.hh.api.VacancyService
import ru.kpfu.itis.gabdullina.hh.favourites.domain.repository.VacancyRepository
import javax.inject.Inject

class VacancyUseCase @Inject constructor(
    private val vacancyRepository: VacancyRepository
) {

    suspend operator fun invoke() : Result<List<Vacancy>?> {
        return UtilityService.runSuspendCatching {
            vacancyRepository.getAll().vacancies.filter {
                it.isFavorite == true
            }
        }
    }
}

object UtilityService {
    inline fun <R> runSuspendCatching(block: () -> R): Result<R> {
        return try {
            Result.success(block())
        } catch(c: CancellationException) {
            throw c
        } catch (e: Throwable) {
            error(e.message.toString() + e.cause.toString())
            Result.failure(e)
        }
    }
}
