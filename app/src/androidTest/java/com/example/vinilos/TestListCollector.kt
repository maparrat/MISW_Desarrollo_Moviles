package com.example.vinilos

import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
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
import com.example.vinilos.view.ListMainColleccionistaActivity
import com.example.vinilos.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestListCollector {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickcollectorButton_navigatesToListCollectorActivity() {
        Intents.init()
        onView(withId(R.id.button3))
            .perform(ViewActions.click())
        Intents.intended(hasComponent(ListMainColleccionistaActivity::class.java.name))
        Intents.release()
    }

    @Test
    fun clickValidateRecyclerviewCollector() {

        Intents.init()
        onView(withId(R.id.button3))
            .perform(ViewActions.click())
        Intents.intended(hasComponent(ListMainColleccionistaActivity::class.java.name))
        onView(withId(R.id.coleccionistaRv))
            .check(matches(isDisplayed()))
        Intents.release()
    }

    @Test
    fun clickValidateDataListCollectorActivity() {

        Intents.init()
        onView(withId(R.id.button3))
            .perform(ViewActions.click())
        // Verify that the intent to launch ListAlbumActivity was sent
        Intents.intended(hasComponent(ListMainColleccionistaActivity::class.java.name))
        Thread.sleep(2000)
        // Verify that the recyclerview is displayed and show the first element of the list
        onView(withId(R.id.coleccionistaRv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(hasDescendant(withText("Jaime Andrés Monsalve Ruiz"))))
        Intents.release()
    }


}


