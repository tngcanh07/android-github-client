package com.tn07.githubapp.data.remote.api.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tn07.githubapp.data.remote.api.ApiConfig
import com.tn07.githubapp.data.remote.api.GithubApi
import com.tn07.githubapp.data.remote.api.GsonUTCDateAdapter
import com.tn07.githubapp.data.remote.api.HeaderInterceptor
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.Date
import javax.net.ssl.HttpsURLConnection

/**
 * Created by toannguyen
 * Jun 27, 2021 at 17:59
 */
class GithubApiIntegrationTest {
    private lateinit var api: GithubApi
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiConfig: ApiConfig
    private val userListResponseJson: String by lazy {
        openResource("user_list.json").use {
            String(it.readBytes())
        }
    }
    private val userDetailResponseJson: String by lazy {
        openResource("user_detail.json").use {
            String(it.readBytes())
        }
    }
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, GsonUTCDateAdapter())
        .create()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        apiConfig = ApiConfig(
            baseUrl = mockWebServer.url("/").toString(),
            userAgent = "mock-user-agent",
            acceptHeader = "mock-accept-header"
        )
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor(apiConfig))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        api = retrofit.create(GithubApi::class.java)
    }

    @Test
    fun searchUsers_success() {
        val pageSize = 1232
        val page = 123
        val query = "mock-query-encoded"

        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                verifyRequiredHeaders(apiConfig, request)

                return MockResponse()
                    .setBody(userListResponseJson)
                    .setResponseCode(HttpsURLConnection.HTTP_OK)
            }
        }

        runBlocking {
            val response = api.searchUsers(query, page, pageSize)

            Assert.assertTrue(response.isSuccessful)
            val body = requireNotNull(response.body())
            Assert.assertEquals(72847229, body.totalCount)
            Assert.assertEquals(30, body.items.size)
        }
    }

    @Test
    fun getUserDetail_success() {
        val username = "mock-user-name"

        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                verifyRequiredHeaders(apiConfig, request)

                return MockResponse()
                    .setBody(userDetailResponseJson)
                    .setResponseCode(HttpsURLConnection.HTTP_OK)
            }
        }

        runBlocking {
            val response = api.getUserDetail(username)

            Assert.assertTrue(response.isSuccessful)
            val body = requireNotNull(response.body())

            Assert.assertEquals(66577, body.id)
            Assert.assertEquals("JakeWharton", body.login)
        }
    }

    private fun verifyRequiredHeaders(apiConfig: ApiConfig, request: RecordedRequest) {
        Assert.assertEquals("Accept", apiConfig.acceptHeader, request.getHeader("Accept"))
        Assert.assertEquals("User-Agent", apiConfig.userAgent, request.getHeader("User-Agent"))
    }

    @Throws(IOException::class)
    private fun openResource(filename: String): FileInputStream {
        val classLoader = requireNotNull(this.javaClass.classLoader)
        val resource = classLoader.getResource(filename)
        val file = File(resource.path)
        return FileInputStream(file)
    }

}