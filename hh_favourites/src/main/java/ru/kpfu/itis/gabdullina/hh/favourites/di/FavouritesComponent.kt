package ru.kpfu.itis.gabdullina.hh.favourites.di

import android.app.Application
import android.content.Context
import dagger.Component
import ru.kpfu.itis.gabdullina.hh.api.VacancyService
import ru.kpfu.itis.gabdullina.hh.favourites.ui.VacancyFragment
import ru.kpfu.itis.gabdullina.hh.favourites.di.module.BinderModule
import ru.kpfu.itis.gabdullina.hh.favourites.di.module.FavouritesModule
import javax.inject.Scope

@Component(
    dependencies = [FavouritesDependencies::class],
    modules = [FavouritesModule::class, BinderModule::class]
)
@FavouritesScope
interface FavouritesComponent {
    @Component.Builder
    interface Builder{

        fun dependencies(favouritesDependencies: FavouritesDependencies): Builder

        fun build(): FavouritesComponent
    }

    fun inject(fragment: VacancyFragment)
}

interface FavouritesDependencies {
    val vacancyService: VacancyService
}

interface FavouritesDependenciesProvider {
    val dependencies: FavouritesDependencies
}

val Context.favouritesDependenciesProvider: FavouritesDependenciesProvider
    get() = when(this) {
        is FavouritesDependenciesProvider -> this
        is Application -> error("Application must implements FavouritesDependencies")
        else -> applicationContext.favouritesDependenciesProvider
    }

@Scope
annotation class FavouritesScope
