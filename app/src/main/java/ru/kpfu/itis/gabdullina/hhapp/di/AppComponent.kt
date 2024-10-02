package ru.kpfu.itis.gabdullina.hhapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.kpfu.itis.gabdullina.hh.api.VacancyService
import ru.kpfu.itis.gabdullina.hh.favourites.di.FavouritesDependencies
import ru.kpfu.itis.gabdullina.hh.list.di.MainDependencies
import javax.inject.Scope

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent: FavouritesDependencies, MainDependencies {

    override val vacancyService: VacancyService

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun provideContext(ctx: Context): Builder

        fun build(): AppComponent
    }
}

@Module
class AppModule{

    @Provides
    @AppScope
    fun provideVacancyService(): VacancyService {
        return VacancyService()
    }
}


@Scope
internal annotation class AppScope
