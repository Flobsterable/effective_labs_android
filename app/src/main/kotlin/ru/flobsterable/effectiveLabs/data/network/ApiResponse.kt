package ru.flobsterable.effectiveLabs.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.flobsterable.effectiveLabs.data.models.Resource
import java.io.IOException

interface ApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        return try {
            withContext(Dispatchers.IO){
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return@withContext Resource.Success(body)
                }
            }
            error("${response.code()} ${response.message()}")}
        } catch (e: IOException) {
            error(e.message ?: e.toString())
        }
    }
    private fun <T> error(errorMessage: String): Resource<T> =
        Resource.Error("Api call failed $errorMessage")
}
