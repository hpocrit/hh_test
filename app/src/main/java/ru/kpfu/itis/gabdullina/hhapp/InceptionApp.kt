package ru.kpfu.itis.gabdullina.hhapp

import android.app.Application
import ru.kpfu.itis.gabdullina.hh.favourites.di.FavouritesDependencies
import ru.kpfu.itis.gabdullina.hh.favourites.di.FavouritesDependenciesProvider
import ru.kpfu.itis.gabdullina.hh.list.di.MainDependencies
import ru.kpfu.itis.gabdullina.hh.list.di.MainDependenciesProvider
import ru.kpfu.itis.gabdullina.hhapp.di.AppComponent
import ru.kpfu.itis.gabdullina.hhapp.di.DaggerAppComponent


class InceptionApp: Application(), FavouritesDependenciesProvider, MainDependenciesProvider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .provideContext(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override val dependencies: FavouritesDependencies = appComponent
    override val mainDependencies: MainDependencies = appComponent
}
