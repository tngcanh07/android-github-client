package com.tn07.githubapp.domain

import com.tn07.githubapp.domain.entities.GitUser
import com.tn07.githubapp.domain.entities.Pageable
import com.tn07.githubapp.domain.entities.SearchConfig
import com.tn07.githubapp.domain.exceptions.DomainException
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


/**
 * Created by toannguyen
 * Jun 26, 2021 at 18:34
 */
@Suppress("UNCHECKED_CAST")
class SearchUsersUseCaseImplTest {
    private lateinit var useCase: SearchUsersUseCaseImpl
    private lateinit var repository: GitUserRepository

    @Before
    fun setUp() {
        repository = Mockito.mock(GitUserRepository::class.java)
        useCase = SearchUsersUseCaseImpl(repository = repository)
    }

    @Test
    fun search_success() {
        val searchConfig = Mockito.mock(SearchConfig::class.java)
        val pageSize = 123
        val page = 3
        val expectedResult = Mockito.mock(Pageable::class.java) as Pageable<GitUser>

        runBlocking {
            Mockito.`when`(
                repository.searchUsers(
                    searchConfig = searchConfig,
                    pageSize = pageSize,
                    page = page
                )
            )
                .thenReturn(expectedResult)

            val result = useCase.searchFor(
                searchConfig = searchConfig,
                pageSize = pageSize,
                page = page
            )

            Mockito.verify(repository).searchUsers(
                searchConfig = searchConfig,
                pageSize = pageSize,
                page = page
            )
            Assert.assertEquals(expectedResult, result)
        }
    }

    @Test(expected = DomainException::class)
    fun search_error() {
        val searchConfig = Mockito.mock(SearchConfig::class.java)
        val pageSize = 1234
        val page = 23
        val expectedException = DomainException()

        runBlocking {
            Mockito.`when`(
                repository.searchUsers(
                    searchConfig = searchConfig,
                    pageSize = pageSize,
                    page = page
                )
            )
                .thenAnswer { throw expectedException }

            useCase.searchFor(searchConfig = searchConfig, pageSize = pageSize, page = page)
        }
    }
}