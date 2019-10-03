package com.diegoferreiracaetano.payment.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.diegoferreiracaetano.commons.moneyMask
import com.diegoferreiracaetano.payment.R

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            moneyMask(this)
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun Button.applyColorEnable() {
    this.setBackgroundResource(R.drawable.around_accent)
    this.isEnabled = true
}
fun TextView.applyColorEnable() = this.setTextColor(ContextCompat.getColor(context,
    R.color.accent
))
fun EditText.applyColorEnable() = this.setTextColor(ContextCompat.getColor(context,
    R.color.accent
))

fun Button.applyColorDisable() {
    this.setBackgroundResource(R.drawable.around)
    this.isEnabled = false
}
fun TextView.applyColorDisable() = this.setTextColor(ContextCompat.getColor(context,
    R.color.secondary_text
))
fun EditText.applyColorDisable() = this.setTextColor(ContextCompat.getColor(context,
    R.color.secondary_text
))
