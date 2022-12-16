package ru.flobsterable.effectiveLabs.data.network.model

import okhttp3.Interceptor
import okhttp3.Response
import ru.flobsterable.effectiveLabs.BuildConfig
import ru.flobsterable.effectiveLabs.data.network.utils.md5
import ru.flobsterable.effectiveLabs.data.network.utils.toHex

class ApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apikey = BuildConfig.APIKEY
        val privateKey = BuildConfig.PrivateAPIKEY
        val ts = 1
        val hash = md5("$ts$privateKey$apikey").toHex()
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("apikey", apikey)
            .addQueryParameter("hash", hash)
            .addQueryParameter("ts", ts.toString())
            .build()
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }
}
