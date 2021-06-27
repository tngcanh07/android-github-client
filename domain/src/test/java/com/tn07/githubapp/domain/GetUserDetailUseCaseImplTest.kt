package com.tn07.githubapp.domain

import com.tn07.githubapp.domain.entities.GitUserDetail
import com.tn07.githubapp.domain.exceptions.DomainException
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by toannguyen
 * Jun 28, 2021 at 00:42
 */
class GetUserDetailUseCaseImplTest {
    private lateinit var useCase: GetUserDetailUseCaseImpl
    private lateinit var repository: GitUserRepository

    @Before
    fun setUp() {
        repository = Mockito.mock(GitUserRepository::class.java)
        useCase = GetUserDetailUseCaseImpl(repository = repository)
    }

    @Test
    fun getUserDetail_success() {
        val username = "mock-user-name"
        val expectedResult = Mockito.mock(GitUserDetail::class.java)

        runBlocking {
            Mockito.`when`(repository.getUserDetail(username)).thenReturn(expectedResult)

            val result = useCase.getUserDetail(username = username)

            Mockito.verify(repository).getUserDetail(username)
            Assert.assertEquals(expectedResult, result)
        }
    }

    @Test(expected = DomainException::class)
    fun getUserDetail_error() {
        val username = "mock-user-name_error"
        val expectedException = DomainException()

        runBlocking {
            Mockito.`when`(repository.getUserDetail(username))
                .thenAnswer { throw expectedException }

            useCase.getUserDetail(username = username)
        }
    }
}