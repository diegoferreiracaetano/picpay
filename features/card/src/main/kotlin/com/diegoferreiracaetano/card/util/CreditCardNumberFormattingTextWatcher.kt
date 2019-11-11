package com.diegoferreiracaetano.card.util

import android.text.Editable
import android.text.TextWatcher

class CreditCardNumberFormattingTextWatcher : TextWatcher {

    private var lock: Boolean = false

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable) {
        if (lock || s.length > 16) {
            return
        }
        lock = true
        var i = 4
        while (i < s.length) {
            if (s.toString()[i] != ' ') {
                s.insert(i, " ")
            }
            i += 5
        }
        lock = false
    }
}
