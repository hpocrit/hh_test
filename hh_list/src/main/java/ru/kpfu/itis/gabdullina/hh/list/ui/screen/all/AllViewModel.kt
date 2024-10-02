package ru.kpfu.itis.gabdullina.hh.list.ui.screen.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kpfu.itis.gabdullina.hh.api.model.Offer
import ru.kpfu.itis.gabdullina.hh.api.model.Vacancy
import ru.kpfu.itis.gabdullina.hh.list.domain.usecase.VacancyUseCase
import javax.inject.Inject

class AllViewModel @Inject constructor(
    private val vacancyUseCase: VacancyUseCase
): ViewModel() {
    private val _vacancyList = MutableStateFlow<List<Vacancy>?>(null)
    val vacancyList : StateFlow<List<Vacancy>?>
        get() = _vacancyList

    fun getVacancies() {
        viewModelScope.launch {
            vacancyUseCase().onSuccess {
                _vacancyList.value = it
            }
        }
    }
}
