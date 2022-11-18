package ru.flobsterable.effectiveLabs.data.network.model

import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.flobsterable.effectiveLabs.data.models.Resource

internal class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, Resource<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<Resource<T>>) {
        proxy.enqueue(ResultCallback(this, callback))
    }

    override fun cloneImpl(): ResultCall<T> {
        return ResultCall(proxy.clone())
    }

    private class ResultCallback<T>(
        private val proxy: ResultCall<T>,
        private val callback: Callback<Resource<T>>
    ) : Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {
            val result: Resource<T> = when (response.isSuccessful) {
                true -> {
                    val body = response.body()
                    Resource.Success(body!!)
                }
                false -> Resource.Error(response.message())
            }
            callback.onResponse(proxy, Response.success(result))
        }

        override fun onFailure(call: Call<T>, error: Throwable) {
            val result = Resource.Error<T>("$error")
            callback.onResponse(proxy, Response.success(result))
        }
    }

    override fun timeout(): Timeout {
        return proxy.timeout()
    }
}
