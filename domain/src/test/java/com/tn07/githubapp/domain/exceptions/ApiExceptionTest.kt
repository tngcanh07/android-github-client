package com.tn07.githubapp.domain.exceptions

import org.junit.Assert
import org.junit.Test

/**
 * Created by toannguyen
 * Jun 28, 2021 at 00:53
 */
class ApiExceptionTest {
    @Test
    fun createException_defaultParams() {
        val httpCode = 423
        val exception = ApiException(httpCode)

        Assert.assertNull(exception.message)
        Assert.assertNull(exception.cause)
        Assert.assertEquals(httpCode, exception.httpCode)
    }

    @Test
    fun createException_withoutCause() {
        val httpCode = 423
        val exception = ApiException(httpCode, null)

        Assert.assertNull(exception.message)
        Assert.assertNull(exception.cause)
        Assert.assertEquals(httpCode, exception.httpCode)
    }

    @Test
    fun createException_withAllParams() {
        val httpCode = 423
        val cause = Exception()

        val exception = ApiException(httpCode, cause)

        Assert.assertNull(exception.message)
        Assert.assertEquals(cause, exception.cause)
        Assert.assertEquals(httpCode, exception.httpCode)
    }
}