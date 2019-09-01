package com.diegoferreiracaetano.contacts

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.diegoferreiracaetano.domain.Interactor
import de.hdodenhof.circleimageview.CircleImageView

fun<T> execute(mediator : MediatorLiveData<Result<T>>, interactor : Interactor<T>) {

    val liveData = liveData {
        try {
            emit(Result.success(interactor.execute()))
        } catch(e: Exception) {
            emit(Result.failure(e))
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