package com.tn07.githubapp.data.remote.api

import com.tn07.githubapp.domain.exceptions.ApiException
import com.tn07.githubapp.domain.exceptions.ConnectionException
import com.tn07.githubapp.domain.exceptions.DomainException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by toannguyen
 * Jun 27, 2021 at 13:16
 */
internal suspend fun <T : Any> request(func: suspend () -> Response<T>): T {
    try {
        val response = func()
        return if (response.isSuccessful) {
            response.body() ?: throw DomainException("NO_RESPONSE_BODY")
        } else throw ApiException(response.code())
    } catch (e: Exception) {
        throw e.mapToDomainException()
    }
}

internal fun Exception.mapToDomainException(): Exception {
    return when (this) {
        is DomainException -> this
        is SocketTimeoutException, is IOException, is UnknownHostException, is NoRouteToHostException, is ConnectException -> {
            ConnectionException(this)
        }
        is HttpException -> ApiException(httpCode = this.code(), cause = this)
        else -> this
    }
}