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
class TestCreateAlbum  {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)



    @Test
    fun clickValidateButtonCreateAlbum() {

        Intents.init()
        Thread.sleep(2000)
        onView(withId(R.id.button1))
            .perform(ViewActions.click())

        //validate that the button is displayed
        Intents.intended(hasComponent(ListMainAlbumActivity::class.java.name))
        onView(withId(R.id.button_create_album))
            .perform(ViewActions.click())
        // Verify that the intent to launch createAlbumActivity was sent
        Intents.intended(hasComponent(CreateAlbumActivity::class.java.name))
        // Verify that the button is displayed
        onView(withId(R.id.title_create_album))
            .check(matches(isDisplayed()))

        Intents.release()
    }

//fill the form and click on the button
@Test
fun fillFormAndClickButton() {
    Intents.init()
    onView(withId(switch_collector))
        .perform(ViewActions.click())
    // Click on the button to navigate to ListMainAlbumActivity
    onView(withId(R.id.button1))
        .perform(ViewActions.click())

    // Validate that the button is displayed
    Intents.intended(hasComponent(ListMainAlbumActivity::class.java.name))
    onView(withId(R.id.button_create_album))
        .perform(ViewActions.click())
    // Verify that the intent to launch CreateAlbumActivity was sent
    Intents.intended(hasComponent(CreateAlbumActivity::class.java.name))
    // Verify that the title is displayed
    onView(withId(R.id.title_create_album))
        .check(matches(isDisplayed()))
    // Fill the form
    onView(withId(R.id.input_name))
        .perform(ViewActions.typeText("A Night at the Opera"))
    onView(withId(R.id.input_cover))
        .perform(ViewActions.typeText("https://upload.wikimedia.org/wikipedia/en/6/67/Queen_A_Night_at_the_Opera.png"))

    // Handle DatePicker and set the date
    onView(withId(R.id.input_release_date))
        .perform(ViewActions.click()) // Open the DatePicker
    onView(isAssignableFrom(DatePicker::class.java))
        .perform(PickerActions.setDate(1975, 11, 21)) // Set the date
    onView(withId(android.R.id.button1))
        .perform(ViewActions.click()) // Confirm the selected date

    onView(withId(R.id.input_description))
        .perform(ViewActions.typeText("A Night at the Opera is the fourth studio album by the British rock band Queen."))
    onView(withId(R.id.input_genre))
        .perform(ViewActions.typeText("Rock"))
    onView(withId(R.id.input_record_label))
        .perform(ViewActions.typeText("EMI / Elektra"))
    // Click on the button
    onView(withId(R.id.button_submit))
        .perform(ViewActions.click())
    // Verify that the RecyclerView is displayed
    onView(withId(R.id.albumsRv))
        .check(matches(isDisplayed()))
    Intents.release()
}


}
