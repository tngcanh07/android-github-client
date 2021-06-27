package com.tn07.githubapp.data

import com.tn07.githubapp.data.datamodel.GitUserDataModel
import com.tn07.githubapp.data.datamodel.UserListResponse
import com.tn07.githubapp.data.remote.GitUserRemoteDataSource
import com.tn07.githubapp.domain.entities.SearchConfig
import com.tn07.githubapp.domain.exceptions.DomainException
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by toannguyen
 * Jun 26, 2021 at 22:20
 */
class GitUserRepositoryImplTest {
    private lateinit var repository: GitUserRepositoryImpl
    private lateinit var remoteDataSource: GitUserRemoteDataSource

    @Before
    fun setUp() {
        remoteDataSource = Mockito.mock(GitUserRemoteDataSource::class.java)
        repository = GitUserRepositoryImpl(remoteDataSource)
    }

    @Test
    fun searchUsers_success() {
        val searchConfig = Mockito.mock(SearchConfig::class.java)
        val pageSize = 1232
        val page = 123

        val expectedResult = UserListResponse(
            totalCount = 333444,
            items = listOf(GitUserDataModel("login", 1234, null))
        )

        runBlocking {
            Mockito.`when`(
                remoteDataSource.searchUsers(
                    searchConfig = searchConfig,
                    page = page,
                    pageSize = pageSize
                )
            )
                .thenReturn(expectedResult)

            val result = repository.searchUsers(
                searchConfig = searchConfig,
                page = page,
                pageSize = pageSize
            )

            Assert.assertEquals(expectedResult.totalCount, result.totalCount)
            Assert.assertEquals(expectedResult.items, result.items)
            Assert.assertEquals(page, result.page)
            Assert.assertEquals(pageSize, result.pageSize)
        }
    }

    @Test(expected = DomainException::class)
    fun searchUsers_error() {
        val searchConfig = Mockito.mock(SearchConfig::class.java)
        val pageSize = 1232
        val page = 123

        val expectedException = DomainException("searchUsers_error")

        runBlocking {
            Mockito.`when`(
                remoteDataSource.searchUsers(
                    searchConfig = searchConfig,
                    page = page,
                    pageSize = pageSize
                )
            )
                .thenAnswer { throw expectedException }

            repository.searchUsers(
                searchConfig = searchConfig,
                page = page,
                pageSize = pageSize
            )
        }
    }
}
