package com.diegoferreiracaetano.card.ui

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.commons.asLiveData
import com.diegoferreiracaetano.domain.card.CardInteractor
import com.diegoferreiracaetano.domain.user.FindContactsByIdInteractor

internal class CardViewModel(private val interactor: CardInteractor) : ViewModel() {

    fun card(userId: Int) = interactor.execute(userId).asLiveData()

}