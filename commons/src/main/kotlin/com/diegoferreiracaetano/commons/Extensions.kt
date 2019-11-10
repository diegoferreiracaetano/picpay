package com.diegoferreiracaetano.commons

import android.content.Context
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.text.DateFormat
import java.text.Normalizer
import java.text.NumberFormat
import java.util.*

private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()
private val DEFAULT_LOCALE = Locale("pt", "BR")

fun <T> Flow<T>.asLiveData(): LiveData<Result<T>> {
    return liveData { collect {
        try {
            emit(Result.success(it))
        } catch (e: Throwable) {
            emit(Result.failure(e))
        }
    } }
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

fun CharSequence.unaccent(): String {
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return REGEX_UNACCENT.replace(temp, "")
}

fun Float.format(): String = NumberFormat
    .getCurrencyInstance(DEFAULT_LOCALE).format(this )

fun EditText.moneyMask(textWatcher: TextWatcher) {
    val s = this.text.toString()
    if (s.isEmpty()) return
    removeTextChangedListener(textWatcher)
    val parsed = s.removeMask().toFloat().div(100)
    val formatted = parsed.format().removeSymbol()
    setText(formatted)
    setSelection(formatted.length)
    addTextChangedListener(textWatcher)
}

fun String.removeSymbol() = replace("[R$]".toRegex(), "").trim()

fun String.removeMask() = replace("[R$,.]".toRegex(), "").trim()

fun Date.format(formatDate: Int) = DateFormat.getDateInstance(formatDate, DEFAULT_LOCALE).format(this)

fun Date.formatTime(formatDate: Int) = DateFormat.getTimeInstance(formatDate, DEFAULT_LOCALE).format(this)

fun Date.format(formatDate: Int, formatTime: Int) = DateFormat.getDateTimeInstance(formatDate, formatTime, DEFAULT_LOCALE).format(this)

fun Long.formatCard() = this.toString().replace("\\d{4}".toRegex(), "$0 ")