package com.bodakesatish.skeleton.data.di.interceptors

import android.content.Context
import com.bodakesatish.skeleton.data.common.NetworkException
import com.bodakesatish.skeleton.data.common.NetworkHelper
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!NetworkHelper.isNetworkAvailable(context)) {
            throw NetworkException()
        }

        return chain.proceed(request)
    }
}

