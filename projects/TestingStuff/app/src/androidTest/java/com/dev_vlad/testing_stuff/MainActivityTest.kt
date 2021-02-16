package com.dev_vlad.testing_stuff

import android.nfc.tech.IsoDep
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    //a test rule called before each function
    @Rule
    @JvmField
    var rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun launchDetailsAct() {

       onView(withId(R.id.next_btn))
           .perform(click())
        onView(withId(R.id.details_container))
            .check(matches(isDisplayed()))

    }


}