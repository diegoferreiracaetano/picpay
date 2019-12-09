package com.diegoferreiracaetano.commons

import android.content.Context
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.diegoferreiracaetano.router.Router
import de.hdodenhof.circleimageview.CircleImageView
import java.text.DateFormat
import java.text.NumberFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

private val DEFAULT_LOCALE = Locale("pt", "BR")

fun <T> Flow<T>.asLiveData(): LiveData<Result<T>> {

    return liveData {
        try {
            collect { emit(Result.success(it)) }
        } catch (e: Throwable) {
            emit(Result.failure(e))
        }
    }
}

fun CircleImageView.setImageUrl(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(true)
            .into(this)
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun Float.format(): String = NumberFormat
    .getCurrencyInstance(DEFAULT_LOCALE).format(this)

fun EditText.moneyMask(textWatcher: TextWatcher) {
    val s = this.text.toString()
    if (s.isEmpty()) return
    removeTextChangedListener(textWatcher)
    val parsed = s.removeMask()
    val formatted = parsed.format().removeSymbol()
    setText(formatted)
    setSelection(formatted.length)
    addTextChangedListener(textWatcher)
}

fun String.removeSymbol() = replace("[R$]".toRegex(), "").trim()

fun String.removeMask() = replace("[R$,.]".toRegex(), "").trim().toFloat().div(100)

fun Date.format(formatDate: Int) = DateFormat.getDateInstance(formatDate, DEFAULT_LOCALE).format(this)

fun Date.formatTime(formatDate: Int) = DateFormat.getTimeInstance(formatDate, DEFAULT_LOCALE).format(this)

fun Date.format(formatDate: Int, formatTime: Int) = DateFormat.getDateTimeInstance(formatDate, formatTime, DEFAULT_LOCALE).format(this)

fun Long.formatCard() = this.toString().replace("\\d{4}".toRegex(), "$0 ")

fun View.navigate(router: Router, any: Any) {
    setOnClickListener {
        findNavController().navigate(Uri.parse(router.navigate(any)))
    }
}

fun RecyclerView.navigate(router: Router, any: Any) {
    findNavController().navigate(Uri.parse(router.navigate(any)))
}

fun Fragment.navigate(router: Router, any: Any) {

    if (router.isStart()) {
        val options = NavOptions.Builder()
            .setPopUpTo(findNavController().graph.startDestination, true)
            .build()
        findNavController().navigate(Uri.parse(router.navigate(any)), options)
    } else
        findNavController().navigate(Uri.parse(router.navigate(any)))
}

fun View.showKeyboard() {
    this.requestFocus()
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}
