package com.diegoferreiracaetano.card.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.diegoferreiracaetano.card.R
import com.diegoferreiracaetano.card.util.CreditCardDateFormattingTextWatcher
import com.diegoferreiracaetano.card.util.CreditCardNumberFormattingTextWatcher
import com.diegoferreiracaetano.commons.Router
import com.diegoferreiracaetano.domain.card.Card
import kotlinx.android.synthetic.main.fragment_card.card_btn
import kotlinx.android.synthetic.main.fragment_card.card_cvv
import kotlinx.android.synthetic.main.fragment_card.card_date
import kotlinx.android.synthetic.main.fragment_card.card_name
import kotlinx.android.synthetic.main.fragment_card.card_number
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
        card_number.editText?.addTextChangedListener(CreditCardNumberFormattingTextWatcher())
        card_date.editText?.addTextChangedListener(CreditCardDateFormattingTextWatcher())
        showButton()

        card_btn.setOnClickListener {
            if (showButton()) {
                viewModel.saveCard(Card(
                    number = card_number.editText?.text.toString().replace("\\s".toRegex(), "").toLong(),
                    name = card_name.editText?.text.toString(),
                    date = card_date.editText?.text.toString(),
                    cvv = card_cvv.editText?.text.toString().toInt()
                )).observe(this, Observer {
                    it.onSuccess(::showSaveCard)
                        .onFailure(::showError)
                })
            }
        }

        viewModel.card(id).observe(this, Observer {
            it.onSuccess(::showCard)
                .onFailure(::showError)
        })
    }

    private fun showCard(card: Card) {
    }

    private fun showSaveCard(boolean: Boolean) {
        val id = requireArguments().getInt(EXTRA_ID)
        val url = Router(requireContext()).Card().next(id)
        findNavController().navigate(url)
    }

    private fun showError(throwable: Throwable) {
    }

    private fun showButton() = (card_number.editText!!.text.isNotEmpty() &&
        card_name.editText!!.text.isNotEmpty() &&
        card_date.editText!!.text.isNotEmpty() &&
        card_cvv.editText!!.text.isNotEmpty())

    companion object {
        private const val EXTRA_ID = "id"
    }
}
