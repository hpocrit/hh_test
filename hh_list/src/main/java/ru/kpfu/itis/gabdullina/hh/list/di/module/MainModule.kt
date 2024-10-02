package ru.kpfu.itis.gabdullina.hh.list.di.module

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.kpfu.itis.gabdullina.hh.list.ui.screen.all.AllViewModel
import ru.kpfu.itis.gabdullina.hh.list.ui.screen.main.MainViewModel
import ru.kpfu.itis.gabdullina.hh.list.utils.ViewModelKey

@Module
internal class MainModule {
    @Provides
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(viewModel: MainViewModel): ViewModel = viewModel

    @Provides
    @[IntoMap ViewModelKey(AllViewModel::class)]
    fun provideAllViewModel(viewModel: AllViewModel): ViewModel = viewModel
}

