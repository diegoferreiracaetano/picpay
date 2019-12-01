package com.diegoferreiracaetano.users.ui

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
internal class UsersViewModelTest {

    /*  @get:Rule
      val rule = InstantTaskExecutorRule()

      private val interactor = mockk<UserInteractor>()
      private val observe = mockk<Observer<Result<List<User>>>>()
      private val observeString = mockk<Observer<String>>()
      private lateinit var viewModel: UsersViewModel

      private val testDispatcher = TestCoroutineDispatcher()

      @Before
      fun setUp() {
          Dispatchers.setMain(testDispatcher)
          viewModel = UsersViewModel(interactor)
      }

      @After
      fun tearDown() {
          Dispatchers.resetMain()
          testDispatcher.cleanupTestCoroutines()
      }

      @Test
      fun `Given interactor users When call fetchusers Then verify result success`() {

          coEvery { interactor.execute(Unit) } returns Mock.users()

          viewModel.fetchUsers()
          viewModel.users.observeForever(observe)

          verify { observe.onChanged(Result.success(Mock.users())) }
      }

      @Test
      fun `Given interactor users When call fetchusers Then verify result error`() {

          val error = Throwable("error")

          coEvery { interactor.execute(Unit) } throws error

          viewModel.fetchUsers()
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


     */
}
