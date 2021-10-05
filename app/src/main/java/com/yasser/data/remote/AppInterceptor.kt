package com.yasser.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

/**
 *Created by Yasser.Elnagar on 28/09/2021
 */

class AppInterceptor @Inject constructor ():Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        Timber.v(request.url().toString())
        val response = chain.proceed(request)
        return response
    }
}