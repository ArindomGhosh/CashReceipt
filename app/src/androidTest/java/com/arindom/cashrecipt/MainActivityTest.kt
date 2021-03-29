package com.arindom.cashrecipt

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_is_main_navigation_fragment_container_visible_for_phone() {
        onView(allOf(withId(R.id.main_nav_host_fragment),
            withContentDescription(R.string.small_main_activity_container_description)))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.main_nav_host_fragment),
            withContentDescription(R.string.small_main_activity_container_description)))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }


    @Test
    fun test_is_main_navigation_fragment_container_visible_for_tablet() {
        onView(allOf(withId(R.id.main_nav_host_fragment),
            withContentDescription(R.string.large_main_activity_container_description)))
            .check(matches(isDisplayed()))
    }
}