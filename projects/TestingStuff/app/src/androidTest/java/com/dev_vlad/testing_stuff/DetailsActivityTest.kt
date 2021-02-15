package com.dev_vlad.testing_stuff

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailsActivityTest{

    //a test rule called before each function
    @Rule
    @JvmField
    var rule = ActivityScenarioRule(DetailsActivity::class.java)

    @Test
    fun isActivityLaunched() {

        onView(ViewMatchers.withId(R.id.details_container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun isBackTitleVisible(){
        //the button is visible
        onView(
            ViewMatchers.withId(R.id.back_btn))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

                    //the button has the title we expect
                    onView (ViewMatchers.withId(R.id.back_btn))
                .check(ViewAssertions.matches(withText(R.string.back_str)))
    }
}