package com.diegoferreiracaetano.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.diegoferreiracaetano.domain.Interactor
import de.hdodenhof.circleimageview.CircleImageView

fun<T> ViewModel.execute(interactor : Interactor<T>): LiveData<Result<T>> {

    return liveData {
        try {
            emit(Result.success(interactor.execute()))
        } catch(ioException: Exception) {
            emit(Result.failure(ioException))
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