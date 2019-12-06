package com.diegoferreiracaetano.card.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.diegoferreiracaetano.card.R
import com.diegoferreiracaetano.card.util.CreditCardDateFormattingTextWatcher
import com.diegoferreiracaetano.card.util.CreditCardNumberFormattingTextWatcher
import com.diegoferreiracaetano.commons.afterTextChanged
import com.diegoferreiracaetano.commons.hideKeyboard
import com.diegoferreiracaetano.commons.navigate
import com.diegoferreiracaetano.commons.showKeyboard
import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.router.Router
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_card.card_btn
import kotlinx.android.synthetic.main.fragment_card.card_cvv
import kotlinx.android.synthetic.main.fragment_card.card_date
import kotlinx.android.synthetic.main.fragment_card.card_name
import kotlinx.android.synthetic.main.fragment_card.card_number
import kotlinx.android.synthetic.main.fragment_card.card_toolbar
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

    override fun onStop() {
        super.onStop()
        card_number.editText?.hideKeyboard()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEdit()
        setupToolbar()
        setupCard()
    }

    private fun setupCard() {
        viewModel.card().observe(this, Observer {
            it.onSuccess {
                showCard(it)
            }.onFailure {
                showError(it)
            }
        })
    }

    private fun setupToolbar() {
        card_toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupEdit() {
        card_number.editText?.showKeyboard()
        card_number.editText?.addTextChangedListener(CreditCardNumberFormattingTextWatcher())
        card_date.editText?.addTextChangedListener(CreditCardDateFormattingTextWatcher())
        card_cvv.editText?.afterTextChanged {
            if (it.length == CVV_LENGTH)
                card_btn.visibility = VISIBLE
            else
                card_btn.visibility = GONE
        }

        card_btn.setOnClickListener {
            if (card_btn.isVisible) {
                viewModel.saveCard(Card(
                    id = 0,
                    number = card_number.editText?.text.toString().replace("\\s".toRegex(), "").toLong(),
                    name = card_name.editText?.text.toString(),
                    date = card_date.editText?.text.toString(),
                    cvv = card_cvv.editText?.text.toString().toInt(),
                    brand = "MASTERCARD"
                )).observe(this, Observer {
                    it.onSuccess(::showSaveCard)
                        .onFailure(::showError)
                })
            }
        }
    }

    private fun showCard(card: Card?) {
        card?.let {
            card_number.editText?.setText(it.number.toString())
            card_name.editText?.setText(it.name)
            card_date.editText?.setText(it.date)
            card_cvv.editText?.setText(it.cvv.toString())
        }
    }

    private fun showSaveCard(card: Pair<Long, Router>) {
        val id = requireArguments().getLong(EXTRA_ID)
        navigate(card.second, id)
    }

    private fun showError(throwable: Throwable) {
        Snackbar.make(requireView(), throwable.message.toString(), Snackbar.LENGTH_LONG).show()
    }

    companion object {
        private const val EXTRA_ID = "id"
        private const val CVV_LENGTH = 3
    }
}
