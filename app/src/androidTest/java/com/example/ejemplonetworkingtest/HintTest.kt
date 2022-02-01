package com.example.ejemplonetworkingtest

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.ejemplonetworkingtest.data.ApiModule
import com.example.ejemplonetworkingtest.data.ServicesApiInterface
import com.example.ejemplonetworkingtest.view.PersonajeActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@UninstallModules(ApiModule::class)
@HiltAndroidTest
class HintTest {
    @get:Rule val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var baseUrl : String

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun addition_isCorrect() {
        println(baseUrl)
        assertEquals(4, 2 + 2)
    }

    @Test
    fun urlModificadaTest() {
        assertEquals("https://estoEsUnaUrlfalsa.falsa", baseUrl)
    }

    @Test
    fun testActivity() {
        val scenario = launchActivity<PersonajeActivity>()
        runBlocking {
            withContext(Dispatchers.IO) {
                delay(1000)
                Espresso.onView(withId(R.id.textViewError))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            }
        }
    }


    @Module
    @InstallIn(SingletonComponent::class)
    object TestApiModule  {
        @Singleton
        @Provides
        fun getBaseUrl(): String {
            //return "https://fedeperin-harry-potter-api.herokuapp.com"
            return "https://estoEsUnaUrlfalsa.falsa"
        }
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
            .baseUrl(getBaseUrl())
            .client(okHttpClient)
            .build()

        @Singleton
        @Provides
        fun provideApiService(retrofit: Retrofit): ServicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java)
    }
}