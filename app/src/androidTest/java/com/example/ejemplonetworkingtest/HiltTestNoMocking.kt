package com.example.ejemplonetworkingtest

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.ejemplonetworkingtest.view.PersonajeActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class HiltTestNoMocking {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var baseUrl : String

    @Before
    fun setUp() {
        hiltRule.inject()
    }


    @Test
    fun launchActivityTestErrorAppears(){
        var scenario = launchActivity<PersonajeActivity>()
        runBlocking {
            withContext(Dispatchers.IO) {
                delay(1000)
                Espresso.onView(ViewMatchers.withId(R.id.textViewError))
                    .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
            }
        }
    }

    @Test
    fun testUrlWasModified(){
        println(baseUrl)
        Assert.assertEquals("https://fedeperin-harry-potter-api.herokuapp.com", baseUrl)
    }

}
