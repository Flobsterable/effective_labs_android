package ru.flobsterable.effectiveLabs.di

import dagger.Binds
import ru.flobsterable.effectiveLabs.navigation.AppNavigationImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.flobsterable.effectiveLabs.navigation.AppNavigation
import ru.flobsterable.effectiveLabs.data.repository.Repository
import ru.flobsterable.effectiveLabs.data.repository.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Singleton
    @Binds
    fun bindNavigation(
        appNavigationImpl: AppNavigationImpl
    ): AppNavigation

    @Singleton
    @Binds
    fun bindRepository(
        repositoryImpl: RepositoryImpl
    ): Repository
}
