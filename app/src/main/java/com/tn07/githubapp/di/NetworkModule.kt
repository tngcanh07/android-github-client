package com.tn07.githubapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tn07.githubapp.BuildConfig
import com.tn07.githubapp.data.remote.api.ApiConfig
import com.tn07.githubapp.data.remote.api.GithubApi
import com.tn07.githubapp.data.remote.api.GsonUTCDateAdapter
import com.tn07.githubapp.data.remote.api.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import javax.inject.Singleton

/**
 * Created by toannguyen
 * Jun 27, 2021 at 14:22
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Date::class.java, GsonUTCDateAdapter())
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        apiConfig: ApiConfig,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiConfig.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiConfig(): ApiConfig {
        return ApiConfig(
            baseUrl = "https://api.github.com/",
            userAgent = BuildConfig.APPLICATION_ID,
            acceptHeader = "application/vnd.github.v3+json"
        )
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
        if(BuildConfig.DEBUG) {
            val log = HttpLoggingInterceptor()
            log.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(log)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }
}