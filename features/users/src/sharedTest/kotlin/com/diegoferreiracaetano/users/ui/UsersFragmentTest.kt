package com.diegoferreiracaetano.users.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diegoferreiracaetano.Mock
import com.diegoferreiracaetano.router.card.CardRouter
import com.diegoferreiracaetano.toLiveDataResultTest
import com.diegoferreiracaetano.users.R
import io.mockk.coEvery
import io.mockk.mockk
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

@RunWith(AndroidJUnit4::class)
class UsersFragmentTest : AutoCloseKoinTest() {

    private val viewModel = mockk<UsersViewModel>()

    @Before
    fun before() {
        startKoin { }

        loadKoinModules(module(override = true) {
            single { viewModel }
        })
    }

    @Test
    fun givenStartScreen_shouldTextNameUser() {
        coEvery { viewModel.users() } returns Mock.users().toLiveDataResultTest(CardRouter())
        coEvery { viewModel.job() } returns true.toLiveDataResultTest()

        launchFragmentInContainer<UsersFragment>(
            fragmentArgs = Bundle.EMPTY,
            themeResId = R.style.AppTheme
        )

        onView(withId(R.id.user_recycle))
            .check(matches(hasDescendant(withText("User"))))
    }

    @Test
    fun givenStartScreen_shouldTextNickName() {
        coEvery { viewModel.users() } returns Mock.users().toLiveDataResultTest(CardRouter())
        coEvery { viewModel.job() } returns true.toLiveDataResultTest()

        launchFragmentInContainer<UsersFragment>(
            fragmentArgs = Bundle.EMPTY,
            themeResId = R.style.AppTheme
        )

        onView(withId(R.id.user_recycle))
            .check(matches(hasDescendant(withText("@username"))))
    }
}
