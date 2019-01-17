package com.pppp.network.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


class RetrofitClient : Client {
    private val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    private val client: OkHttpClient = OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.flickr.com/")
        .client(client)
        .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy())))
        .build()
    private val service = retrofit.create(Api::class.java)

    override fun getPics() = service.getPics()
}