package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.dto.Header
import com.example.darkfox.trainingnotes.utils.extensions.*
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.AccountManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetModule {


    val module = module {
        single { provideOkHttpClient(get()) }
        single { provideConverterFactory(get()) }
        single { provideCallAdapterFactory() }
        single { provideGson() }
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

    private fun provideOkHttpClient(accountManager: AccountManager) = OkHttpClient.Builder().also { clientBuilder ->
        clientBuilder.addInterceptor { chain ->
            chain.request().also { request ->
                if (accountManager.isTokenExist) {
                    request addHeader header {
                        name = AUTH_HEADER_NAME
                        headerValue = "$AUTH_PREFIX ${accountManager.restoreAccessToken()!!}"
                    }
                }
            } build chain
        }
        clientBuilder.cache()
    }

    private val AUTH_PREFIX = "bearer "
    private val AUTH_HEADER_NAME = "Authorization"
}