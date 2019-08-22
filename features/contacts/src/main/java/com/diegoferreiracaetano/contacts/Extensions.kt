package com.diegoferreiracaetano.contacts

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.diegoferreiracaetano.domain.Interactor
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun<T> ViewModel.execute(result: MutableLiveData<ResultData<T>>, interactor : Interactor<T>) {

    viewModelScope.launch(Dispatchers.IO) {
        try {
            result.postValue(ResultData.Loading)
            val res = interactor.execute()
            result.postValue(ResultData.Success(res))
        } catch (e: Throwable) {
            result.postValue(ResultData.Error(e))
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