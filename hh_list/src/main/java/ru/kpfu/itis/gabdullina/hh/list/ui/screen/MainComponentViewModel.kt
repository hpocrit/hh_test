package ru.kpfu.itis.gabdullina.hh.list.ui.screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.kpfu.itis.gabdullina.hh.list.di.DaggerMainComponent
import ru.kpfu.itis.gabdullina.hh.list.di.MainComponent
import ru.kpfu.itis.gabdullina.hh.list.di.mainDependenciesProvider

internal class MainComponentViewModel(
    application: Application
): AndroidViewModel(application) {

    val mainComponent: MainComponent by lazy {
        DaggerMainComponent.builder()
            .dependencies(application.mainDependenciesProvider.mainDependencies)
            .build()
    }

}
