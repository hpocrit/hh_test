package ru.kpfu.itis.gabdullina.hh.favourites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kpfu.itis.gabdullina.hh.api.model.Vacancy
import ru.kpfu.itis.gabdullina.hh.favourites.domain.VacancyUseCase
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    private val vacancyUseCase: VacancyUseCase
) : ViewModel() {

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
