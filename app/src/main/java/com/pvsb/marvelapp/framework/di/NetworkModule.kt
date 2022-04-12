package com.pvsb.marvelapp.framework.di

import com.pvsb.marvelapp.BuildConfig
import com.pvsb.marvelapp.framework.network.interceptor.MarvelApiInterceptor
import com.pvsb.marvelapp.framework.network.service.MarvelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_UNIT = 15L

    @Provides
    fun okHttpProvider(): OkHttpClient =
        OkHttpClient().newBuilder().apply {
            connectTimeout(TIMEOUT_UNIT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_UNIT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT_UNIT, TimeUnit.SECONDS)
            addInterceptor(MarvelApiInterceptor(BuildConfig.PUBLIC_KEY, BuildConfig.PRIVATE_KEY))

            val logInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            if (BuildConfig.DEBUG) {
                addInterceptor(logInterceptor)
            }
        }.build()

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @BaseUrlInjection baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideService(retrofit: Retrofit): MarvelService = retrofit.create(MarvelService::class.java)

}