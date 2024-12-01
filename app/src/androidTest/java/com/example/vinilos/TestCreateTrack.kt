package com.example.vinilos

import android.os.Parcel
import android.os.Parcelable
import android.widget.DatePicker
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.util.HumanReadables
import androidx.test.rule.ActivityTestRule
import com.example.vinilos.R.id.switch_collector
import com.example.vinilos.view.AlbumFragment
import com.example.vinilos.view.CreateAlbumActivity
import com.example.vinilos.view.ListMainAlbumActivity
import com.example.vinilos.view.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestCreateTrack  {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun debugRecyclerView() {
        onView(withId(R.id.albumsRv))
            .check { view, _ ->
                println(HumanReadables.describe(view))
            }
    }
    @Test
    fun clickFirstAlbumAndValidateDisplayed() {
        Intents.init()

        onView(withId(R.id.button1))
            .perform(ViewActions.click())
        intended(hasComponent(ListMainAlbumActivity::class.java.name))
        Thread.sleep(2000)
        onView(withId(R.id.albumsRv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(hasDescendant(withText("Buscando América"))))

        onView(withId(R.id.albumsRv))
            .check(matches(isDisplayed()))

        Intents.release()
    }
    @Test
    fun clickFirstAlbumAndValidateDetail() {
        Intents.init()

        onView(withId(R.id.button1))
            .perform(ViewActions.click())
        intended(hasComponent(ListMainAlbumActivity::class.java.name))
        Thread.sleep(5000)
        onView(withId(R.id.albumsRv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(hasDescendant(withText("Buscando América"))))

        // Paso 1: Esperar y asegurarse de que el RecyclerView está visible
        onView(withId(R.id.albumsRv))
            .check(matches(isDisplayed()))

        // Paso 2: Hacer clic en el primer elemento del RecyclerView
        onView(withId(R.id.albumsRv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, // Primera posición
                    ViewActions.click()
                )
            )
        Intents.release()
    }


    @Test
    fun clickFirstAlbumAndValidateFields() {
        Intents.init()

        onView(withId(R.id.button1))
            .perform(ViewActions.click())
        intended(hasComponent(ListMainAlbumActivity::class.java.name))
        Thread.sleep(2000)
        onView(withId(R.id.albumsRv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(hasDescendant(withText("Buscando América"))))


        onView(withId(R.id.albumsRv))
            .check(matches(isDisplayed()))
        onView(withId(R.id.albumsRv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, // Primera posición
                    ViewActions.click()
                )
            )


        // Paso 4: Validar los campos en DetailAlbumActivity
        onView(withId(R.id.albumTitle)) // ID del TextView del título
            .check(matches(isDisplayed()))

         //Liberar Intents al final
        Intents.release()
    }

    //validate the button "get track" este visible
    @Test
    fun validateButtonGetTrack() {
        Intents.init()

        onView(withId(R.id.button1))
            .perform(ViewActions.click())
        intended(hasComponent(ListMainAlbumActivity::class.java.name))
        Thread.sleep(2000)
        onView(withId(R.id.albumsRv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(hasDescendant(withText("Buscando América"))))

        onView(withId(R.id.albumsRv))
            .check(matches(isDisplayed()))
        onView(withId(R.id.albumsRv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, // Primera posición
                    ViewActions.click()
                )
            )

        onView(withId(R.id.button_get_track))
            .check(matches(isDisplayed()))

        Intents.release()
    }


}
