package com.tn07.githubapp.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by toannguyen
 * Jun 27, 2021 at 14:29
 */
class HeaderInterceptor @Inject constructor(
    private val apiConfig: ApiConfig
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()
            .newBuilder()
            .header(RequestHeader.ACCEPT, apiConfig.acceptHeader)
            .header(RequestHeader.USER_AGENT, apiConfig.userAgent)

        return chain.proceed(requestBuilder.build())
    }
}