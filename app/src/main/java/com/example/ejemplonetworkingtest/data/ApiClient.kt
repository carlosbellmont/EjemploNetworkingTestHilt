package com.example.ejemplonetworkingtest.data

import com.example.ejemplonetworkingtest.model.PersonajeModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Singleton


interface ServicesApiInterface {
    @GET("/personajes")
    fun obtenerPersonajes(): Call<List<PersonajeModel>>
}

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val API_BASE_URL = "https://fedeperin-harry-potter-api.herokuapp.com"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(API_BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ServicesApiInterface = retrofit.create(ServicesApiInterface::class.java)

//    @Singleton
//    @Provides
//    fun providesRepository(apiService: ServicesApiInterface) = Repository(apiService)
}