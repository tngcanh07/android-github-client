package com.tn07.githubapp.domain.exceptions

import org.junit.Assert
import org.junit.Test

/**
 * Created by toannguyen
 * Jun 26, 2021 at 20:20
 */
class DomainExceptionTest {
    @Test
    fun createException_withoutParams() {
        val exception = DomainException()

        Assert.assertNull(exception.message)
        Assert.assertNull(exception.cause)
    }

    @Test
    fun createException_withMessage() {
        val message = "Error-message-at-${System.currentTimeMillis()}"

        val exception = DomainException(message)


        Assert.assertEquals(message, exception.message)
        Assert.assertNull(exception.cause)
    }

    @Test
    fun createException_withCause() {
        val cause = RuntimeException()

        val exception = DomainException(cause = cause)

        Assert.assertNull(exception.message)
        Assert.assertEquals(cause, exception.cause)
    }

    @Test
    fun createException_withAllParams() {
        val cause = RuntimeException()
        val message = "Error-message-at-${System.currentTimeMillis()}"

        val exception = DomainException(message = message, cause = cause)

        Assert.assertEquals(message, exception.message)
        Assert.assertEquals(cause, exception.cause)
    }
}