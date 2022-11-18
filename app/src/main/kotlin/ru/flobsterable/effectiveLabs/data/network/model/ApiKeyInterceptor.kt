package ru.flobsterable.effectiveLabs.data.network.model

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apikey = "60da449c109e38bdd8ff1d7be9d355b4"
        val hash = "0d2047ca3bad43c823319f0ec86eca8f"
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("apikey", apikey)
            .addQueryParameter("hash", hash)
            .addQueryParameter("ts", "1")
            .build()
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }
}
