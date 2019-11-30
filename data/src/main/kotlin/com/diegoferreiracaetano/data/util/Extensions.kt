package com.diegoferreiracaetano.data.util

import java.text.Normalizer
private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

fun String.unaccent(): String {
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return REGEX_UNACCENT.replace(temp, "")
}