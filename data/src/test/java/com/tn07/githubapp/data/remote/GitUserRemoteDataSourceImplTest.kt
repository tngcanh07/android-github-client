package com.tn07.githubapp.data.remote

import com.tn07.githubapp.domain.entities.SearchConfig
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito

/**
 * Created by toannguyen
 * Jun 27, 2021 at 09:45
 */
class GitUserRemoteDataSourceImplTest {
    private lateinit var remoteDataSource: GitUserRemoteDataSourceImpl

    @Before
    fun setUp() {
//        remoteDataSource = GitUserRemoteDataSourceImpl()
    }

    @Test
    fun searchUsers_success() {
        val searchConfig = Mockito.mock(SearchConfig::class.java)
        val page = 123
        val perPage = 100
        runBlocking {
            val response = remoteDataSource.searchUsers(searchConfig, page, perPage)

            Assert.assertNotNull(response)
            Assert.assertTrue(response.totalCount > 0)
            Assert.assertTrue(response.items.size >= response.totalCount)
        }
    }

    @Test
    fun getUserDetail_success() {
        val username = "mock-user-name"
        runBlocking {
            val response = remoteDataSource.getUserDetail(username)

            Assert.assertNotNull(response)
        }
    }
}