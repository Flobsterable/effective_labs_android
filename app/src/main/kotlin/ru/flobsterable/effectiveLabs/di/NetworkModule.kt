package ru.flobsterable.effectiveLabs.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.flobsterable.effectiveLabs.data.network.NetworkService
import ru.flobsterable.effectiveLabs.data.network.NetworkServiceImpl
import ru.flobsterable.effectiveLabs.data.network.model.ResultAdapterFactory
import ru.flobsterable.effectiveLabs.data.network.ServerApi
import ru.flobsterable.effectiveLabs.data.network.model.ApiKeyInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Singleton
    @Binds
    fun bindNetworkService(
        networkServiceImpl: NetworkServiceImpl
    ): NetworkService

    companion object {
        private const val baseUrl = "https://gateway.marvel.com/"

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addNetworkInterceptor(ApiKeyInterceptor())
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                ).build()
        }

        @Singleton
        @Provides
        fun provideMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        @Singleton
        @Provides
        fun provideRetrofit(
            client: OkHttpClient,
            moshi: Moshi
        ) : Retrofit {
            return Retrofit.Builder().client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(ResultAdapterFactory())
                .build()
        }
        @Singleton
        @Provides
        fun provideApiNetwork(
            retrofit: Retrofit
        ): ServerApi = retrofit.create(ServerApi::class.java)
    }
}
