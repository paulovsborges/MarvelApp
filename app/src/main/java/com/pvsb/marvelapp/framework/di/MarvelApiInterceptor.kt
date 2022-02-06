package com.pvsb.marvelapp.framework.di

import com.pvsb.marvelapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

class MarvelApiInterceptor : Interceptor {

    private companion object {
        const val PUBLIC_KEY = BuildConfig.PUBLIC_KEY
        const val PRIVATE_KEY = BuildConfig.PRIVATE_KEY
        const val RADIX = 16
        const val PAD_LENGTH = 32

        val timestamp = Calendar.getInstance().timeInMillis / 1000
        val ts = timestamp.toString()
        val hash = "$ts$PRIVATE_KEY$PUBLIC_KEY".md5()

        fun String.md5(): String =
            BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
                .toString(RADIX)
                .padStart(PAD_LENGTH, '0')
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("apikey", PUBLIC_KEY)
            .addQueryParameter("ts", ts)
            .addQueryParameter("hash", hash)
            .build()

        val request = originalRequest
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}