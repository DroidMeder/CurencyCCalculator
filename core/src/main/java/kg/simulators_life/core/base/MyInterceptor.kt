package kg.simulators_life.core.base

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return response.newBuilder()
            .addHeader("Content-Type", "application/xml; charset=utf-8")
            .build()
    }
}