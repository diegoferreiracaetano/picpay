package com.diegoferreiracaetano.card.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diegoferreiracaetano.card.R
import com.diegoferreiracaetano.domain.card.Card
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardFragment : Fragment() {

    private val viewModel: CardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.card(id).observeForever {
            it.onSuccess(::showCard)
                .onFailure(::showError)
        }
    }

    private fun showCard(card: Card) {

    }

    private fun showError(throwable: Throwable) {
    }

    companion object {
        private const val EXTRA_ID = "id"
    }
}
