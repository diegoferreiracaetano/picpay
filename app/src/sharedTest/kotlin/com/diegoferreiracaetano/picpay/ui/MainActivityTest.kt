package com.diegoferreiracaetano.picpay.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diegoferreiracaetano.picpay.R
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = lazyActivityScenarioRule<MainActivity>(launchActivity = false)


    @After
    fun after() {
        rule.getScenario().close()
    }

    @Test
    fun useAppContext() {

        rule.launch()

        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()))
    }
}
