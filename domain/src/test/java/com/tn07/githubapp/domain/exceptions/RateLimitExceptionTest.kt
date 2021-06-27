package com.tn07.githubapp.domain.exceptions

import org.junit.Assert
import org.junit.Test

/**
 * Created by toannguyen
 * Jun 28, 2021 at 00:50
 */
class RateLimitExceptionTest {
    @Test
    fun createException() {
        val exception = RateLimitException()
        Assert.assertNull(exception.message)
        Assert.assertNull(exception.cause)
    }
}