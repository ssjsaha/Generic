package com.example.genericapp.di

import android.content.Context
import android.content.Intent
import com.example.genericapp.MainActivity
import com.example.genericapp.network.Api
import com.example.genericapp.network.ResponseAdapterFactory
import com.example.genericapp.network.ThrowableAdapter
import com.example.genericapp.network.UserInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir, "offlineCache")
        return Cache(httpCacheDirectory, (10 * 1024 * 1024).toLong())
    }

    @Singleton
    @Provides
    fun provideLoggerInterceptor() = run {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideUserInterceptor() = UserInterceptor()

    @Singleton
    @Provides
    fun provideResponseAdapter(@ApplicationContext context: Context) : Interceptor {

        val interceptor = Interceptor{
            val userAgentRequest = it.request()
                .newBuilder()
                .addHeader(UserInterceptor.CONTENT_TYPE, UserInterceptor.APPLICATION_JSON)
                .addHeader(UserInterceptor.ACCEPT_VERSION, "v1")
                //
                .build()
            val res = it.proceed(it.request())
            if(res.code == 401){
                val intent = Intent(context , MainActivity::class.java)
                intent.setClass(context , MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context.startActivity(intent)
            }
            res
        }
        return interceptor
    }

    @Singleton
    @Provides
    fun provideResponseAdapter2() = ResponseAdapterFactory()

    @Singleton
    @Provides
    fun provideThrowableAdapter() = ThrowableAdapter()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        userInterceptor: UserInterceptor,
        responseInterceptor : Interceptor
    ) = OkHttpClient.Builder()
        .addNetworkInterceptor(loggingInterceptor)
        .addInterceptor(userInterceptor)
        .addInterceptor(responseInterceptor)
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .build()

    @Singleton
    @Provides
    fun provideMoshi(throwableAdapter: ThrowableAdapter): Moshi = Moshi.Builder()
        .add(throwableAdapter)
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun createRetrofit(
        client: OkHttpClient,
        moshi: Moshi,
        responseAdapterFactory: ResponseAdapterFactory,
    ): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addCallAdapterFactory(responseAdapterFactory)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): Api=
        retrofit.create(Api::class.java)
}
