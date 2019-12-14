package com.diegoferreiracaetano.commons

import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

class SnackbarMessage(
    @StringRes val messageId: Int,
    val longDuration: Boolean,
    val error: Throwable? = null
){

    fun duration() = if(longDuration) Snackbar.LENGTH_LONG else Snackbar.LENGTH_SHORT
}

