package com.diegoferreiracaetano.data.remote.user

import com.diegoferreiracaetano.data.remote.PicpayApi
import com.diegoferreiracaetano.data.remote.transform
import com.diegoferreiracaetano.domain.user.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.any
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit

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