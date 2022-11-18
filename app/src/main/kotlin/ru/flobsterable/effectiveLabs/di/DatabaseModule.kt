package ru.flobsterable.effectiveLabs.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.flobsterable.effectiveLabs.data.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {
    companion object {
        @Singleton
        @Provides
        fun provideData(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

        @Singleton
        @Provides
        fun provideDataDao(db: AppDatabase) = db.dataDao()
    }
}
