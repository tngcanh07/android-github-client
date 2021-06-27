package com.tn07.githubapp.domain.entities

import org.junit.Assert
import org.junit.Test

/**
 * Created by toannguyen
 * Jun 28, 2021 at 00:48
 */
class UserTypeTest {
    @Test
    fun verifyUserTypeId() {
        Assert.assertEquals("user", UserType.User.typeId)
        Assert.assertEquals("org", UserType.Org.typeId)
    }
}