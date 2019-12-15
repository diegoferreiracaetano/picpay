package com.diegoferreiracaetano.data.util

import org.junit.Test

class ExtensionsTest {

    @Test
    fun `Given an accented string when calling unaccent we should display the unaccented string`() {
        assert("áéíóů".unaccent() == "aeiou")
    }
}
