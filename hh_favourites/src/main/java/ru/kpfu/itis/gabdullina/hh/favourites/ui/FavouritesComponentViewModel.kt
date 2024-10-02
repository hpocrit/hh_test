package ru.kpfu.itis.gabdullina.hh.favourites.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.kpfu.itis.gabdullina.hh.favourites.di.DaggerFavouritesComponent
import ru.kpfu.itis.gabdullina.hh.favourites.di.FavouritesComponent
import ru.kpfu.itis.gabdullina.hh.favourites.di.favouritesDependenciesProvider

internal class FavouritesComponentViewModel(
    application: Application
): AndroidViewModel(application) {

    val favouritesComponent: FavouritesComponent by lazy {
        DaggerFavouritesComponent.builder()
            .dependencies(application.favouritesDependenciesProvider.dependencies)
            .build()
    }
}
