package com.tn07.githubapp.data.remote

import com.tn07.githubapp.data.datamodel.GitUserDetailDataModel
import com.tn07.githubapp.data.datamodel.UserListResponse
import com.tn07.githubapp.data.remote.api.GithubApi
import com.tn07.githubapp.domain.entities.SearchConfig
import com.tn07.githubapp.domain.entities.UserType
import com.tn07.githubapp.domain.exceptions.ConnectionException
import com.tn07.githubapp.domain.exceptions.RateLimitException
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response
import java.net.SocketTimeoutException

/**
 * Created by toannguyen
 * Jun 27, 2021 at 09:45
 */
class GitUserRemoteDataSourceImplTest {
    private lateinit var api: GithubApi
    private lateinit var remoteDataSource: GitUserRemoteDataSourceImpl

    @Before
    fun setUp() {
        api = Mockito.mock(GithubApi::class.java)
        remoteDataSource = GitUserRemoteDataSourceImpl(api)
    }

    @Test
    fun searchUsers_success() {
        val searchText = "searchText"
        val searchConfig = mockSearchConfig(searchText, UserType.User)
        val expectedQuery = "$searchText type:user"

        val page = 123
        val perPage = 100
        val expectedResult = Mockito.mock(UserListResponse::class.java)
        val mockApiResponse = Response.success(expectedResult)
        runBlocking {
            Mockito.`when`(api.searchUsers(expectedQuery, page, perPage))
                .thenReturn(mockApiResponse)
            val response = remoteDataSource.searchUsers(searchConfig, page, perPage)

            Assert.assertEquals(expectedResult, response)
        }
    }

    @Test(expected = ConnectionException::class)
    fun searchUsers_error_connectionException() {
        val searchText = "text@${System.currentTimeMillis()}"
        val searchConfig = mockSearchConfig(searchText, UserType.Org)
        val expectedQuery = "$searchText type:org"

        val page = 123
        val perPage = 100
        val expectedException = SocketTimeoutException()
        runBlocking {
            Mockito.`when`(api.searchUsers(expectedQuery, page, perPage))
                .thenAnswer { throw expectedException }
            remoteDataSource.searchUsers(searchConfig, page, perPage)
        }
    }

    @Test
    fun buildQueryString_withoutSearchText() {
        val searchText: String? = null
        val userType = UserType.Org
        val searchConfig = mockSearchConfig(searchText, userType)
        val expectedQuery = "type:${userType.typeId}"

        val result = remoteDataSource.buildSearchQuery(searchConfig)

        Assert.assertEquals(expectedQuery, result)
    }

    @Test
    fun buildQueryString_searchTextEmpty() {
        val searchText = ""
        val userType = UserType.User
        val searchConfig = mockSearchConfig(searchText, userType)
        val expectedQuery = " type:${userType.typeId}"

        val result = remoteDataSource.buildSearchQuery(searchConfig)

        Assert.assertEquals(expectedQuery, result)
    }

    @Test
    fun buildQueryString_withSearchText() {
        val searchText = "text@${System.currentTimeMillis()}"
        val userType = UserType.User
        val searchConfig = mockSearchConfig(searchText, userType)
        val expectedQuery = "$searchText type:${userType.typeId}"

        val result = remoteDataSource.buildSearchQuery(searchConfig)

        Assert.assertEquals(expectedQuery, result)
    }

    @Test
    fun getUserDetail_success() {
        val username = "mock-user-name"
        val userDetail = Mockito.mock(GitUserDetailDataModel::class.java)
        val mockApiResponse = Response.success(userDetail)

        runBlocking {
            Mockito.`when`(api.getUserDetail(username)).thenReturn(mockApiResponse)
            val result = remoteDataSource.getUserDetail(username)

            Assert.assertEquals(userDetail, result)
        }
    }

    @Test(expected = RateLimitException::class)
    fun getUserDetail_error_rateLimit() {
        val username = "mock-user-name"
        val mockApiResponse = Response.error<GitUserDetailDataModel>(
            403,
            "".toResponseBody(null)
        )
        runBlocking {
            Mockito.`when`(api.getUserDetail(username)).thenReturn(mockApiResponse)
            remoteDataSource.getUserDetail(username)
        }
    }

    private fun mockSearchConfig(
        searchText: String? = null,
        userType: UserType = UserType.User
    ): SearchConfig {
        val searchConfig = Mockito.mock(SearchConfig::class.java)
        Mockito.`when`(searchConfig.searchText).thenReturn(searchText)
        Mockito.`when`(searchConfig.userType).thenReturn(userType)

        return searchConfig
    }
}