package com.example.vinilos

import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.vinilos.view.AlbumFragment
import com.example.vinilos.view.ListMainAlbumActivity
import com.example.vinilos.view.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestListAlbum {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickAlbunesButton_navigatesToListAlbumActivity() {
        Intents.init()
        onView(withId(R.id.button1))
            .perform(ViewActions.click())
        Intents.intended(hasComponent(ListMainAlbumActivity::class.java.name))
        Intents.release()
    }

    @Test
    fun clickValidateRecyclerviewAlbum() {

        Intents.init()
        onView(withId(R.id.button1))
            .perform(ViewActions.click())
        Intents.intended(hasComponent(ListMainAlbumActivity::class.java.name))
        onView(withId(R.id.albumsRv))
            .check(matches(isDisplayed()))
        Intents.release()
    }

    @Test
    fun clickValidateDataListAlbumActivity() {

        Intents.init()
        onView(withId(R.id.button1))
            .perform(ViewActions.click())
        // Verify that the intent to launch ListAlbumActivity was sent
        Intents.intended(hasComponent(ListMainAlbumActivity::class.java.name))
        Thread.sleep(2000)
        // Verify that the recyclerview is displayed and show the first element of the list
        onView(withId(R.id.albumsRv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(hasDescendant(withText("Buscando América"))))
        Intents.release()
    }

    @Test
    fun viewDetailAlbum(){
        Intents.init()
        onView(withId(R.id.button1))
            .perform(ViewActions.click())
        intended(hasComponent(ListMainAlbumActivity::class.java.name))
        Thread.sleep(5000)
        onView(withId(R.id.albumsRv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(hasDescendant(withText("Buscando América"))))
        Thread.sleep(5000)
        onView(withText("Buscando América")).check(matches(isDisplayed()))
        Thread.sleep(5000)
        onView(withText("Buscando América")).perform(ViewActions.click())
        Thread.sleep(5000)
        Intents.release()
    }

    @Test
    fun returningToMainMenu(){
        Intents.init()
        onView(withId(R.id.button1))
            .perform(ViewActions.click())
        intended(hasComponent(ListMainAlbumActivity::class.java.name))
        Thread.sleep(5000)
        onView(withId(R.id.albumsRv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(hasDescendant(withText("Buscando América"))))
        Thread.sleep(5000)
        onView(withText("Buscando América")).check(matches(isDisplayed()))
        Thread.sleep(5000)
        onView(withText("Buscando América")).perform(ViewActions.click())
        Thread.sleep(2000)
        pressBack()
        Thread.sleep(2000)
        pressBack()
        Thread.sleep(2000)
        Intents.release()
    }

}
