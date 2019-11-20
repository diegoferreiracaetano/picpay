package com.diegoferreiracaetano.data.remote.user

import com.diegoferreiracaetano.data.remote.PicpayApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class UserRepositoryRemoteTest {

    private val service = mockk<PicpayApi>()
    private lateinit var repositoryRemote: OrderRepositoryRemote

    @Before
    fun setup() {
        repositoryRemote = OrderRepositoryRemote(service)
    }

    @Test
    fun `Given service, When calling users, Then assert values`() {

        runBlocking {

            coEvery { service.users() } returns Mock.listUserEntity()

            val result = repositoryRemote.users()

            assertEquals(result, service.users().transform())
        }
    }
}
