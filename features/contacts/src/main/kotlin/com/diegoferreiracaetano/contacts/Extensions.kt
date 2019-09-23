package com.diegoferreiracaetano.contacts

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.diegoferreiracaetano.domain.Interactor
import de.hdodenhof.circleimageview.CircleImageView
import java.text.Normalizer

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

fun SearchView.applyBackground() {
    setOnQueryTextFocusChangeListener { _, hasFocus ->
        if (hasFocus)
            setBackgroundResource(R.drawable.around_search_selected)
        else
            setBackgroundResource(R.drawable.around_search)
    }
}

fun CharSequence.unaccent(): String {
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return REGEX_UNACCENT.replace(temp, "")
}
