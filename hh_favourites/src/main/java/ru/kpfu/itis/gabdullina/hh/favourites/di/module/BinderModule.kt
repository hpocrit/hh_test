package ru.kpfu.itis.gabdullina.hh.favourites.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.kpfu.itis.gabdullina.hh.favourites.data.repository.VacancyRepositoryImpl
import ru.kpfu.itis.gabdullina.hh.favourites.domain.repository.VacancyRepository
import ru.kpfu.itis.gabdullina.hh.favourites.utils.DaggerViewModelFactory

@Module
interface BinderModule {
    @Binds
    fun bindDaggerFactoryToViewModelFactory(
        impl: DaggerViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    fun bindVacancyRepToImpl(
        impl: VacancyRepositoryImpl
    ): VacancyRepository
}
