package ru.kpfu.itis.gabdullina.hh.list.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kpfu.itis.gabdullina.hh.api.model.Offer
import ru.kpfu.itis.gabdullina.hh.api.model.Vacancy
import ru.kpfu.itis.gabdullina.hh.list.domain.usecase.OfferUseCase
import ru.kpfu.itis.gabdullina.hh.list.domain.usecase.VacancyUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val vacancyUseCase: VacancyUseCase,
    private val offerUseCase: OfferUseCase
): ViewModel() {
    private val _vacancyList = MutableStateFlow<List<Vacancy>?>(null)
    val vacancyList : StateFlow<List<Vacancy>?>
        get() = _vacancyList

    private val _offerList = MutableStateFlow<List<Offer>?>(null)
    val offerList : StateFlow<List<Offer>?>
        get() = _offerList

    fun getVacancies() {
        viewModelScope.launch {
            vacancyUseCase().onSuccess {
                _vacancyList.value = it
            }

            offerUseCase().onSuccess {
                _offerList.value = it
            }
        }
    }
}
