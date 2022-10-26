package ru.flobsterable.effectiveLabs.di

import ru.flobsterable.effectiveLabs.navigation.AppNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.flobsterable.effectiveLabs.navigation.AppNavigation
import ru.flobsterable.effectiveLabs.data.repository.Repository
import ru.flobsterable.effectiveLabs.data.repository.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideNavigation(): AppNavigation = AppNavigationImpl()

    @Singleton
    @Provides
    fun provideRepository(): Repository = RepositoryImpl()
}