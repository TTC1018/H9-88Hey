package com.softeer.mycarchiving.util

import com.softeer.mycarchiving.constant.AUTHORIZATION
import com.softeer.mycarchiving.constant.CONTENT_TYPE
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(
    private val pref: PreferenceUtil
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder().apply {
            if (originalRequest.url.toString().contains("reissue")) {
                addHeader(AUTHORIZATION, "Bearer ${pref.refreshToken}")
            } else {
                addHeader(AUTHORIZATION, "Bearer ${pref.accessToken}")
            }
            addHeader(CONTENT_TYPE, "application/json")
        }.build()
        return chain.proceed(newRequest)
    }
}