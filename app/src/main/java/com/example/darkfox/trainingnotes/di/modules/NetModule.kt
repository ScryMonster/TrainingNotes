package com.example.darkfox.trainingnotes.di.modules

import android.app.Application
import com.example.darkfox.trainingnotes.BuildConfig
import com.example.darkfox.trainingnotes.utils.extensions.addHeader
import com.example.darkfox.trainingnotes.utils.extensions.build
import com.example.darkfox.trainingnotes.utils.extensions.header
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.TokenManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetModule {


    val module = module {
        single { provideCache(get()) }
        single { provideConverterFactory(get()) }
        single { provideCallAdapterFactory() }
        single { provideGson() }
        single { provideOkHttpClient(get(),get()) }
        single { buildRetrofit(get(), get(), get()) }
    }

    private fun buildRetrofit(okHttpClient: OkHttpClient,
                              converter: Converter.Factory,
                              callAdapter: CallAdapter.Factory) = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converter)
            .addCallAdapterFactory(callAdapter)
            .build()

    private fun provideConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    private fun provideCallAdapterFactory(): CallAdapter.Factory = CoroutineCallAdapterFactory()

    private fun provideGson(): Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()

    private fun provideOkHttpClient(tokenManager: TokenManager,
                                    cache: Cache) = OkHttpClient.Builder().also { clientBuilder ->
        clientBuilder.addInterceptor { chain ->
            chain.request().also { request ->
                if (tokenManager.isTokenExist) {
                    request addHeader header {
                        name = AUTH_HEADER_NAME
                        headerValue = "$AUTH_PREFIX ${tokenManager.restoreAccessToken()!!}"
                    }
                }
            } build chain
        }

        if (BuildConfig.DEBUG){
            clientBuilder.addInterceptor(
                    HttpLoggingInterceptor()
                            .apply { level = HttpLoggingInterceptor.Level.BODY }
            )
        }
        clientBuilder.cache(cache)
        clientBuilder.connectTimeout(timeoutSeconds.toLong(), TimeUnit.SECONDS)
                .readTimeout(timeoutSeconds.toLong(), TimeUnit.SECONDS)
                .writeTimeout(timeoutSeconds.toLong(), TimeUnit.SECONDS)

    }.build()

    private fun provideCache(application:Application) : Cache{
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    private val AUTH_PREFIX = "bearer "
    private val AUTH_HEADER_NAME = "Authorization"
    private const val timeoutSeconds = BuildConfig.TIMEOUT_SECONDS
}