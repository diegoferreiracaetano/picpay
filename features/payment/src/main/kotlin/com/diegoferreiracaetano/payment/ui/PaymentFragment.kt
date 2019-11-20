package com.diegoferreiracaetano.payment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diegoferreiracaetano.commons.removeMask
import com.diegoferreiracaetano.commons.removeSymbol
import com.diegoferreiracaetano.commons.setImageUrl
import com.diegoferreiracaetano.domain.payment.Payment
import com.diegoferreiracaetano.domain.transaction.Transaction
import com.diegoferreiracaetano.payment.R
import com.diegoferreiracaetano.payment.util.afterTextChanged
import com.diegoferreiracaetano.payment.util.applyColorDisable
import com.diegoferreiracaetano.payment.util.applyColorEnable
import com.diegoferreiracaetano.router.Router
import kotlinx.android.synthetic.main.fragment_payment.payment_btn_pay
import kotlinx.android.synthetic.main.fragment_payment.payment_img_mask
import kotlinx.android.synthetic.main.fragment_payment.payment_txt_card
import kotlinx.android.synthetic.main.fragment_payment.payment_txt_real
import kotlinx.android.synthetic.main.fragment_payment.payment_txt_username
import kotlinx.android.synthetic.main.fragment_payment.payment_txt_value
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentFragment : Fragment() {

    private val viewModel: PaymentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(EXTRA_ID)
        viewModel.user(id).observe(this, Observer {
            it.onSuccess(::showPayment)
                .onFailure(::showError)
        })
    }

    private fun showPayment(payment: Payment?) {
        payment?.apply {
            payment_img_mask.setImageUrl(user.img)
            payment_txt_username.text = user.name
            payment_txt_value.afterTextChanged {
                it.removeMask().let {
                    value = it
                    if (it > 0) {
                        payment_txt_value.applyColorEnable()
                        payment_txt_real.applyColorEnable()
                        payment_btn_pay.applyColorEnable()
                    } else {
                        payment_txt_value.applyColorDisable()
                        payment_txt_real.applyColorDisable()
                        payment_btn_pay.applyColorDisable()
                    }
                }
            }
            payment_txt_card.text = card.brand.plus(card.number.toString().takeLast(4))
            payment_btn_pay.setOnClickListener {
                viewModel.savePayment(this)
                    .observe(this@PaymentFragment, Observer {
                        it.onSuccess(::showTransaction).onFailure (::showError)
                    })
            }
        }
    }

    private fun showTransaction(pair: Pair<Transaction, Router>) {
        pair.second.navigate(pair.first)
    }

    private fun showError(throwable: Throwable) {
    }

    companion object {
        private const val EXTRA_ID = "id"
    }
}
