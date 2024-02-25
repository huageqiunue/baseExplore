package com.example.fivechessgame.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    companion object {
        const val BASE_URL = "https://example.com"
    }

    @Provides
    fun provideDefaultRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideDefaultOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }
}