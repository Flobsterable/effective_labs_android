package ru.flobsterable.effectiveLabs.data.network.model

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import ru.flobsterable.effectiveLabs.data.models.Resource
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val rawReturnType: Class<*> = getRawType(returnType)

        if (rawReturnType == Call::class.java && returnType is ParameterizedType) {
            val callInnerType: Type = getParameterUpperBound(0, returnType)
            if (getRawType(callInnerType) == Resource::class.java) {
                return when(callInnerType is ParameterizedType) {
                    true -> {
                        val resultInnerType = getParameterUpperBound(0, callInnerType)
                        ResultCallAdapter<Any?>(resultInnerType) }
                    false-> ResultCallAdapter<Nothing>(Nothing::class.java)
                }
            }
        }
        return null
    }
}

private class ResultCallAdapter<R>(private val type: Type) : CallAdapter<R, Call<Resource<R>>> {

    override fun responseType() = type

    override fun adapt(call: Call<R>): Call<Resource<R>> = ResultCall(call)
}
