package com.pvsb.core.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

class MarvelApiInterceptor : Interceptor {

    private companion object {
        const val PUBLIC_KEY = "3827cfb8b6ff40a51f3fed47938660c2"
        const val PRIVATE_KEY = "b2d65258840e5d37dca4eadd9b1b78241b4d82a2"
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
        val url = originalRequest.url().newBuilder()
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