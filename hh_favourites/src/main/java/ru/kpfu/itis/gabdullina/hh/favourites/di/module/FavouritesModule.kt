package ru.kpfu.itis.gabdullina.hh.favourites.di.module

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.kpfu.itis.gabdullina.hh.favourites.ui.FavouritesViewModel
import ru.kpfu.itis.gabdullina.hh.favourites.utils.ViewModelKey

@Module
internal class FavouritesModule {
    @Provides
    @[IntoMap ViewModelKey(FavouritesViewModel::class)]
    fun provideFavouritesViewModel(viewModel: FavouritesViewModel): ViewModel = viewModel
}
