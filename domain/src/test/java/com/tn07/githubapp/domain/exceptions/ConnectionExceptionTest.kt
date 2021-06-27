package com.tn07.githubapp.domain.exceptions

import org.junit.Assert
import org.junit.Test
import java.net.UnknownHostException

/**
 * Created by toannguyen
 * Jun 28, 2021 at 00:52
 */
class ConnectionExceptionTest {
    @Test
    fun createException() {
        val cause = UnknownHostException()
        val exception = ConnectionException(cause)

        Assert.assertNull(exception.message)
        Assert.assertEquals(cause, exception.cause)
    }
}