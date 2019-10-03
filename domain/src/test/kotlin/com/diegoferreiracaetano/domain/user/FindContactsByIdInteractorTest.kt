package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Mock.user
import com.diegoferreiracaetano.domain.Mock.users
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class FindContactsByIdInteractorTest {
    private val repository = mockk<UserRepository>()
    private lateinit var interactor: FindContactsByIdInteractor

    @Before
    fun setUp() {
        interactor = FindContactsByIdInteractor(repository)
    }

    @Test
    fun `Given repository, When calling users, Then assert values`() {

        runBlocking {

            coEvery { repository.users() } returns users()

            val result = interactor.execute(1)

            assertEquals(result, user())
        }
    }
}
