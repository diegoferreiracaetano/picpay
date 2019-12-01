package com.diegoferreiracaetano.users.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.diegoferreiracaetano.Mock
import com.diegoferreiracaetano.domain.user.UserInteractor
import com.diegoferreiracaetano.domain.user.User
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class usersViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val interactor = mockk<UserInteractor>()
    private val observe = mockk<Observer<Result<List<User>>>>()
    private val observeString = mockk<Observer<String>>()
    private lateinit var viewModel: usersViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = usersViewModel(interactor)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Given interactor users When call fetchusers Then verify result success`() {

        coEvery { interactor.execute(Unit) } returns Mock.users()

        viewModel.fetchusers()
        viewModel.users.observeForever(observe)

        verify { observe.onChanged(Result.success(Mock.users())) }
    }

    @Test
    fun `Given interactor users When call fetchusers Then verify result error`() {

        val error = Throwable("error")

        coEvery { interactor.execute(Unit) } throws error

        viewModel.fetchusers()
        viewModel.users.observeForever(observe)

        verify { observe.onChanged(Result.failure(error)) }
    }

    @Test
    fun `Given interactor users When call search Then verify search`() {

        every { observeString.onChanged(any()) } returns Unit

        viewModel.search("string")

        viewModel.search.observeForever(observeString)

        verify { observeString.onChanged("string") }
    }
}
