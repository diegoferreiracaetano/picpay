package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Mock.users
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class ContactsInteractorTest {

    private val repository = mockk<UserRepository>()
    private lateinit var interactor: UserInteractor

    @Before
    fun setUp() {
        interactor = UserInteractor(repository)
    }

    @Test
    fun `Given repository, When calling users, Then assert values`() {

        runBlocking {

            coEvery { repository.users() } returns users()

            val result = interactor.execute(Unit)

            assertEquals(result, users())
        }
    }
}
