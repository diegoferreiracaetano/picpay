package com.diegoferreiracaetano.users.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.diegoferreiracaetano.Mock
import com.diegoferreiracaetano.domain.ResultRouter
import com.diegoferreiracaetano.domain.receipt.ReceiptInteractor
import com.diegoferreiracaetano.domain.user.SyncUserInteractor
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.domain.user.UserInteractor
import com.diegoferreiracaetano.router.card.CardRouter
import com.diegoferreiracaetano.toResultSuccessTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class UsersViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val userInteractor = mockk<UserInteractor>()
    private val receiptInteractor = mockk<ReceiptInteractor>()
    private val syncUserInteractor = mockk<SyncUserInteractor>()

    private lateinit var viewModel: UsersViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UsersViewModel(userInteractor, receiptInteractor, syncUserInteractor)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Given interactor users When call users Then verify result success`() {

        val observer = mockk<Observer<Result<ResultRouter<List<User>>>>>()
        val result = Mock.users().toResultSuccessTest(CardRouter())

        coEvery { userInteractor.invoke("") } returns result
        viewModel.users().observeForever(observer)

        coVerify { observer.onChanged(result.single()) }
    }

    @Test
    fun `Given interactor users When call search Then verify search`() {

        val observer = mockk<Observer<Result<ResultRouter<List<User>>>>>()
        val result = Mock.users().toResultSuccessTest(CardRouter())

        coEvery { userInteractor.invoke("string") } returns result

        viewModel.search("string")
        viewModel.users().observeForever(observer)

        coVerify { observer.onChanged(result.single()) }
    }

    @Test(expected = Throwable::class)
    fun `Given interactor users When call users Then verify result error`() {

        val observer = mockk<Observer<Result<ResultRouter<List<User>>>>>()
        val result = Throwable("error")

        coEvery { userInteractor.invoke("") } throws result
        viewModel.users().observeForever(observer)

        verify { observer.onChanged(Result.failure(result)) }
    }
}
