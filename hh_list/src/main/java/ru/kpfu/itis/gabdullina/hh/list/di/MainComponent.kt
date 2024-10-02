package ru.kpfu.itis.gabdullina.hh.list.di

import android.app.Application
import android.content.Context
import dagger.Component
import ru.kpfu.itis.gabdullina.hh.api.VacancyService
import ru.kpfu.itis.gabdullina.hh.list.ui.screen.main.MainFragment
import ru.kpfu.itis.gabdullina.hh.list.di.module.BinderModule
import ru.kpfu.itis.gabdullina.hh.list.di.module.MainModule
import ru.kpfu.itis.gabdullina.hh.list.ui.screen.all.AllFragment
import javax.inject.Scope

@Component(
    dependencies = [MainDependencies::class],
    modules = [MainModule::class, BinderModule::class]
)
@MainScope
interface MainComponent {
    @Component.Builder
    interface Builder{

        fun dependencies(mainDependencies: MainDependencies): Builder

        fun build(): MainComponent
    }

    fun inject(fragment: MainFragment)
    fun inject(fragment: AllFragment)
}

interface MainDependencies {
    val vacancyService: VacancyService
}

interface MainDependenciesProvider {
    val mainDependencies: MainDependencies
}

val Context.mainDependenciesProvider: MainDependenciesProvider
    get() = when(this) {
        is MainDependenciesProvider -> this
        is Application -> error("Application must implements MainDependencies")
        else -> applicationContext.mainDependenciesProvider
    }

@Scope
annotation class MainScope
