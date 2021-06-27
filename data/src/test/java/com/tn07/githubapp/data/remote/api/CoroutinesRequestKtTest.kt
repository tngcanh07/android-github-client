package com.tn07.githubapp.data.remote.api

import com.tn07.githubapp.domain.exceptions.ApiException
import com.tn07.githubapp.domain.exceptions.ConnectionException
import com.tn07.githubapp.domain.exceptions.DomainException
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

/**
 * Created by toannguyen
 * Jun 27, 2021 at 18:00
 */
class CoroutinesRequestKtTest {
    @Test
    fun request_success() {
        val message = "message-at-${System.currentTimeMillis()}"
        val response = Response.success(message)

        runBlocking {
            val result = request {
                response
            }
            Assert.assertEquals(message, result)
        }
    }

    @Test
    fun request_error_noResponseBody() {
        val response = Response.success<String>(null)

        runBlocking {
            var expectedError: DomainException? = null
            try {
                request {
                    response
                }
            } catch (apiError: DomainException) {
                expectedError = apiError
            }

            requireNotNull(expectedError)
            Assert.assertNull(expectedError.cause)
        }
    }

    @Test
    fun request_error_apiException() {
        val errorCode = 456
        val exception = Mockito.mock(HttpException::class.java)
        Mockito.`when`(exception.code()).thenReturn(errorCode)

        runBlocking {
            var expectedError: ApiException? = null
            try {
                request<String> {
                    throw exception
                }
            } catch (apiError: ApiException) {
                expectedError = apiError
            }

            requireNotNull(expectedError)
            Assert.assertEquals(errorCode, expectedError.httpCode)
            Assert.assertEquals(exception, expectedError.cause)
        }
    }

    @Test
    fun request_error_connectionException() {
        val exception = Mockito.mock(UnknownHostException::class.java)

        var expectedError: ConnectionException? = null
        try {
            runBlocking {
                request<String> {
                    throw exception
                }
            }
        } catch (apiError: ConnectionException) {
            expectedError = apiError
        }

        requireNotNull(expectedError)
        Assert.assertEquals(exception, expectedError.cause)
    }
}