package ru.flobsterable.effectiveLabs.di

import dagger.Binds
import ru.flobsterable.effectiveLabs.navigation.AppNavigationImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.flobsterable.effectiveLabs.data.network.NetworkService
import ru.flobsterable.effectiveLabs.data.network.NetworkServiceImpl
import ru.flobsterable.effectiveLabs.navigation.AppNavigation
import ru.flobsterable.effectiveLabs.data.repository.Repository
import ru.flobsterable.effectiveLabs.data.repository.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule

@Module
@InstallIn(SingletonComponent::class)
interface AppBindModule {

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

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Singleton
    @Binds
    fun bindNetworkService(
        networkServiceImpl: NetworkServiceImpl
    ): NetworkService
}
