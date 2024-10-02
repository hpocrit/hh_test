package ru.kpfu.itis.gabdullina.hh.list.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.kpfu.itis.gabdullina.hh.list.data.repository.VacancyRepositoryImpl
import ru.kpfu.itis.gabdullina.hh.list.domain.repository.VacancyRepository
import ru.kpfu.itis.gabdullina.hh.list.utils.DaggerViewModelFactory

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
