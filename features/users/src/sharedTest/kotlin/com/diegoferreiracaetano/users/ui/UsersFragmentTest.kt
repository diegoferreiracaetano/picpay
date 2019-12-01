package com.diegoferreiracaetano.users.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.not
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest

@RunWith(AndroidJUnit4::class)
class UsersFragmentTest : AutoCloseKoinTest() {

    /* private val repository = mockk<UserRepository>()
    private lateinit var interactor: UserInteractor
    private lateinit var viewModel: UsersViewModel

    @Before
    fun before() {
        interactor = UserInteractor(repository)
        viewModel = UsersViewModel(interactor)
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


*/
}
