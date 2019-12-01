package com.diegoferreiracaetano.users.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diegoferreiracaetano.Mock
import com.diegoferreiracaetano.users.R
import com.diegoferreiracaetano.domain.user.UserInteractor
import com.diegoferreiracaetano.domain.user.UserRepository
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
class usersFragmentTest : AutoCloseKoinTest() {

    private val repository = mockk<UserRepository>()
    private lateinit var interactor: UserInteractor
    private lateinit var viewModel: usersViewModel

    @Before
    fun before() {
        interactor = UserInteractor(repository)
        viewModel = usersViewModel(interactor)
        startKoin { }

        loadKoinModules(module(override = true) {
            single { repository }
            single { viewModel }
            single { interactor }
        })
    }

    @Test
    fun givenStartScreen_shouldTextTitle() {
        coEvery { repository.users() } returns Mock.users()

        launchFragmentInContainer<usersFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.user_title))
            .check(matches(withText(R.string.user_title)))
    }

    @Test
    fun givenStartScreen_whenRepositoryListSuccess_shouldDisplayList() {
        coEvery { repository.users() } returns Mock.users()

        launchFragmentInContainer<usersFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.user_recycle)).check(matches(isDisplayed()))
    }

    @Test
    fun givenStartScreen_whenRepositoryListError_shouldErrorView() {
        coEvery { repository.users() } throws Exception()

        launchFragmentInContainer<usersFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.user_error)).check(matches(isDisplayed()))
    }

    @Test
    fun whenCallingonStop_shouldSimmerNotVisible() {
        coEvery { repository.users() } returns Mock.users()

        val scenario = launchFragmentInContainer<usersFragment>(themeResId = R.style.AppTheme)

        scenario.onFragment {
            it.onStop()
        }

        onView(withId(R.id.shimmer_view_container)).check(matches(not(isDisplayed())))
    }

    @Test
    fun givenStartScreen_whenRepositoryListSuccess_verifySearchIsVisible() {
        coEvery { repository.users() } returns Mock.users()

        launchFragmentInContainer<usersFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.searchView)).check(matches(isDisplayed()))
    }

    @Test
    fun givenStartScreen_whenIsWriteSearch_shouldSomething() {
        coEvery { repository.users() } returns Mock.users()

        launchFragmentInContainer<usersFragment>(themeResId = R.style.AppTheme)

        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText("something"))
    }

    @Test
    fun givenStartScreen_whenRepositoryListError_shouldVerifyNotNullListener() {
        coEvery { repository.users() } throws Exception()

        launchFragmentInContainer<usersFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.error_btn)).perform(click())
    }
}
