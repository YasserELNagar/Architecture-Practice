package com.yasser.di

import com.yasser.data.remote.AppInterceptor
import com.yasser.data.remote.service.MainServices
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *Created by Yasser.Elnagar on 28/09/2021
 */
@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {


    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }


    @Singleton
    @Provides
    fun provideOkHttp(interceptor: AppInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    }


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://e-signature.57357.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideMainServices(retrofit: Retrofit.Builder): MainServices {
        return retrofit
            .build()
            .create(MainServices::class.java)
    }

}