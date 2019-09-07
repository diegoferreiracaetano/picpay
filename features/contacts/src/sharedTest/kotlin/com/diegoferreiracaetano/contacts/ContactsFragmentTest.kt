package com.diegoferreiracaetano.contacts

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diegoferreiracaetano.Mock
import com.diegoferreiracaetano.contacts.view.ContactsFragment
import com.diegoferreiracaetano.contacts.view.ContactsViewModel
import com.diegoferreiracaetano.domain.user.ContactsInteractor
import com.diegoferreiracaetano.domain.user.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

@RunWith(AndroidJUnit4::class)
class ContactsFragmentTest : AutoCloseKoinTest() {

    private val repository = mockk<UserRepository>()
    private lateinit var interactor: ContactsInteractor
    private lateinit var viewModel: ContactsViewModel

    @Before
    fun before() {
        interactor = ContactsInteractor(repository)
        viewModel = ContactsViewModel(interactor)
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

        launchFragmentInContainer<ContactsFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.contact_title))
            .check(matches(withText(R.string.contact_title)))
    }

    @Test
    fun givenStartScreen_whenRepositoryListSuccess_shouldDisplayList() {
        coEvery { repository.users() } returns Mock.users()

        launchFragmentInContainer<ContactsFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.contact_recycle)).check(matches(isDisplayed()))
    }

    @Test(expected = Throwable::class)
    fun givenStartScreen_whenRepositoryListError_shouldDisplayToastError() {

        coEvery { repository.users() } throws Throwable("error")

        val scenario = launchFragmentInContainer<ContactsFragment>(themeResId = R.style.AppTheme)
        var activity: FragmentActivity? = null

        scenario.onFragment {
            activity = it.requireActivity()
        }

        activity?.let {
            onView(withText(R.string.contacts_msg_error))
                .inRoot(withDecorView(not(it.window.decorView)))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun test(){
        assert(false)
    }
}
