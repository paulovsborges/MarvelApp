package com.pvsb.marvelapp.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Calendar

class MarvelApiInterceptor(
    private val publicKey: String,
    private val privateKEy: String
) : Interceptor {

    private val timestamp = Calendar.getInstance().timeInMillis / 1000
    private val ts = timestamp.toString()
    private val hash = "$ts$privateKEy$publicKey".md5()

    private fun String.md5(): String =
        BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
            .toString(16)
            .padStart(32, '0')

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("apikey", publicKey)
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