package com.pppp.network.client

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class RetrofitClient(BASE_URL: String = "https://api.flickr.com/") : Client {
    private val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    private val client: OkHttpClient = OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy())))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    private val service = retrofit.create(Api::class.java)

    override fun getPics() = service.getPics()
}