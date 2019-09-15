package com.diegoferreiracaetano.domain.user

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class UserTest {

    lateinit var user: User

    @Before
    fun setUp() {
        user = User(id = 0, name = "name", username = "username", img = null)
    }

    @Test
    fun `when call id should value zero`() {

        assertEquals(0, user.id)
    }

    @Test
    fun `when call name should value name`() {

        assertEquals("name", user.name)
    }

    @Test
    fun `when call username should value username`() {

        assertEquals("username", user.username)
    }

    @Test
    fun `when call img should value img null`() {

        assertNull(user.img)
    }

    @Test
    fun `when call img should value img not null`() {
        user = User(id = 0, name = "name", username = "username", img = "http://www.google.com/img.jpg")

        assertNotNull(user.img)
    }
}
