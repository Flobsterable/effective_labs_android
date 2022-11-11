package ru.flobsterable.effectiveLabs.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.flobsterable.effectiveLabs.data.network.model.Result
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.flobsterable.effectiveLabs.data.models.Resource
import java.io.IOException
import javax.inject.Inject

class NetworkServiceImpl @Inject constructor(): NetworkService, ApiResponse {

    private val apikey = "60da449c109e38bdd8ff1d7be9d355b4"
    private val hash = "0d2047ca3bad43c823319f0ec86eca8f"
    private val baseUrl = "https://gateway.marvel.com/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit
        .Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val networkService = retrofit.create(ServerApi::class.java)

    override suspend fun getCharacterList(): Resource<List<Result>> {
        return when (val result = safeApiCall { networkService.getHeroesList(apikey, hash, 1) }){
            is Resource.Error -> Resource.Error(result.message.toString())
            is Resource.Success -> try {
                Resource.Success(result.data!!.data.results)
            } catch (e: IOException) { return Resource.Error(e.message.toString()) }
        }
    }

    override suspend fun getCharacterInfo(id: Int): Resource<Result> {
        return when (val result = safeApiCall { networkService.getHeroInfo(id.toString(), apikey, hash, 1) }) {
            is Resource.Error -> Resource.Error(result.message.toString())
            is Resource.Success -> try {
                Resource.Success(result.data!!.data.results[0])
            } catch (e: IOException) { return Resource.Error(e.message.toString()) }
        }
    }
}
