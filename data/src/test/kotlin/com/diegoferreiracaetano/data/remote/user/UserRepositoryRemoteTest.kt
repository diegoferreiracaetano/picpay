package com.diegoferreiracaetano.data.remote.user

import com.diegoferreiracaetano.data.remote.PicpayApi
import com.diegoferreiracaetano.data.remote.transform
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class UserRepositoryRemoteTest {

    private val service = mockk<PicpayApi>()
    private lateinit var repositoryRemote: UserRepositoryRemote

    @Before
    fun setup() {
        repositoryRemote = UserRepositoryRemote(service)
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
