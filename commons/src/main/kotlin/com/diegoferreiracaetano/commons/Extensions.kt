package com.diegoferreiracaetano.commons

import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.diegoferreiracaetano.domain.Interactor
import de.hdodenhof.circleimageview.CircleImageView
import java.text.Normalizer
import java.text.NumberFormat
import java.util.*

private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

fun <T> execute(mediator: MediatorLiveData<Result<T>>, interactor: Interactor<T>) {

    val liveData = liveData {
        try {
            emit(Result.success(interactor.execute()))
        } catch (e: Throwable) {
            emit(Result.failure<T>(e))
        }
    }

    mediator.addSource(liveData, mediator::postValue)
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

fun Float.fomart() = NumberFormat
    .getCurrencyInstance(Locale("pt", "BR")).format(this / 100)


fun EditText.moneyMask(textWatcher: TextWatcher) {
    val s = this.text.toString()
    if (s.isEmpty()) return
    removeTextChangedListener(textWatcher)
    val parsed = s.removeMask().toFloat()
    val formatted = parsed.fomart().removeSymbol()
    setText(formatted)
    setSelection(formatted.length)
    addTextChangedListener(textWatcher)
}

fun String.removeSymbol() = replace("[R$]".toRegex(), "").trim()

fun String.removeMask() = replace("[R$,.]".toRegex(), "").trim()