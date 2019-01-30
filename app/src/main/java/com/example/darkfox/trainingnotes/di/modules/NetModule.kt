package com.example.darkfox.trainingnotes.di.modules

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

    private fun buildRetrofit(okHttpClient: OkHttpClient,
                              converter: Converter.Factory,
                              callAdapter: CallAdapter.Factory) = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converter)
            .addCallAdapterFactory(callAdapter)
            .build()

    private fun provideConverterFactory(gson: Gson):Converter.Factory = GsonConverterFactory.create(gson)

    private fun provideCallAdapterFactory():CallAdapter.Factory = CoroutineCallAdapterFactory()

    private fun provideGson(): Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()

    private fun provideOkHttpClient() =

    val module = module{
        single { provideConverterFactory(get()) }
        single { provideCallAdapterFactory() }
        single { provideGson() }
        single { buildRetrofit(get(),get(),get()) }
    }
}