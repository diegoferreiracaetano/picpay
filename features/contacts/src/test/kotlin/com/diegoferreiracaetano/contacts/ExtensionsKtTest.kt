package com.diegoferreiracaetano.contacts

import de.hdodenhof.circleimageview.CircleImageView
import io.mockk.mockk
import org.junit.Test

class ExtensionsKtTest {

    private val image = mockk<CircleImageView>()

    @Test
    fun `Given an accented string when calling unaccent we should display the unaccented string`() {
        assert("áéíóů".unaccent() == "aeiou")
    }
}
