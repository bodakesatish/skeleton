package com.bodakesatish.skeleton.data.di.module


import android.app.Application
import android.content.Context
import com.bodakesatish.skeleton.data.di.interceptors.NetworkConnectionInterceptor
import com.bodakesatish.skeleton.data.source.remote.datasource.AppService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    @Named("appServiceClient")
    internal fun provideOkHTTPClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.callTimeout(60, TimeUnit.SECONDS)
        client.readTimeout(60, TimeUnit.SECONDS)
        client.connectTimeout(60, TimeUnit.SECONDS)
        client.writeTimeout(60, TimeUnit.SECONDS)
//        if (DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            client.interceptors().add(logging)
            client.addInterceptor(networkConnectionInterceptor)
//        }
        return client.build()
    }
//
//    @Provides
//    @Singleton
//    internal fun provideNetworkConnectionInterceptor(context: Context): NetworkConnectionInterceptor {
//        return NetworkConnectionInterceptor(context)
//    }

    @Provides
    @Singleton
    @Named("retrofitBuilderDataService")
    internal fun provideRetrofitBuilderForDataService(
        @Named("appServiceClient") okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.mfapi.in/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideDataService(@Named("retrofitBuilderDataService") retrofit: Retrofit): AppService {
        return retrofit.create<AppService>(AppService::class.java)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

}