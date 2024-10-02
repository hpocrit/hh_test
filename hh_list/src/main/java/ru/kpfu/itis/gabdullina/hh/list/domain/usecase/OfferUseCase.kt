package ru.kpfu.itis.gabdullina.hh.list.domain.usecase

import ru.kpfu.itis.gabdullina.hh.api.VacancyService
import ru.kpfu.itis.gabdullina.hh.api.model.Offer
import ru.kpfu.itis.gabdullina.hh.list.domain.repository.VacancyRepository
import ru.kpfu.itis.gabdullina.hh.list.utils.UtilityService
import javax.inject.Inject

class OfferUseCase @Inject constructor(
    private val vacancyRepository: VacancyRepository
) {
    suspend operator fun invoke() : Result<List<Offer>?> {
        return UtilityService.runSuspendCatching {
            vacancyRepository.getAll().offers
        }
    }
}
