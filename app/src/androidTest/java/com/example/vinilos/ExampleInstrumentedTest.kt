package com.example.vinilos

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.vinilos.view.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.vinilos", appContext.packageName)
    }

    @Test
    fun verifyAlbumsButtonAppearsOnLaunch() {
        // Check that a button with the text "Albums" is displayed
        onView(withText("Albunes"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun verifyArtistasButtonAppearsOnLaunch() {
        // Check that a button with the text "Artistas" is displayed
        onView(withText("Artistas"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun verifyCollectorButtonAppearsOnLaunch() {
        // Check that a swich button with the text "Soy Colleccionista" is displayed
        onView(withText("Soy coleccionista"))
            .check(matches(isDisplayed()))
    }
}
