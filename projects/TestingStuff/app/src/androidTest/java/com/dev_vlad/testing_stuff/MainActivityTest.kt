package com.dev_vlad.testing_stuff

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
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
    fun isActivityLaunched() {

       onView(withId(R.id.main_container))
           .check(matches(isDisplayed()))
    }

    @Test
    fun isNextTitleVisible(){
        //the button is visible
        onView(withId(R.id.next_btn))
            .check(matches(isDisplayed()))

        //the button has the title we expect
        onView(withId(R.id.next_btn))
            .check(matches(withText(R.string.next_str)))
    }

}