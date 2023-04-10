package com.example.madcourse.di

import com.example.madcourse.BuildConfig
import com.example.madcourse.domain.network.api.PostsApi
import com.example.madcourse.domain.network.api.ProfileDetailsApi
import com.example.madcourse.domain.network.api.UserApi
import com.example.madcourse.domain.network.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    @Named(Constant.retrofit_profile)
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://random-data-api.com/")
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    @Named(Constant.USER_API)
    fun provideUserRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    @Named(Constant.retrofit_post)
    fun providePostRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://picsum.photos/v2/")
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideGithubApi(@Named(Constant.retrofit_profile) retrofit: Retrofit): ProfileDetailsApi {
        return retrofit.create(ProfileDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun providePostApi(@Named(Constant.retrofit_post) retrofit: Retrofit): PostsApi {
        return retrofit.create(PostsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApi(@Named(Constant.USER_API) retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

}