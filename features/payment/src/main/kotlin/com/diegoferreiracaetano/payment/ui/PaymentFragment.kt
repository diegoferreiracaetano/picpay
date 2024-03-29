package com.diegoferreiracaetano.payment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.diegoferreiracaetano.commons.hideKeyboard
import com.diegoferreiracaetano.commons.navigate
import com.diegoferreiracaetano.commons.removeMask
import com.diegoferreiracaetano.commons.setImageUrl
import com.diegoferreiracaetano.commons.showKeyboard
import com.diegoferreiracaetano.domain.payment.Payment
import com.diegoferreiracaetano.payment.R
import com.diegoferreiracaetano.payment.util.afterTextChanged
import com.diegoferreiracaetano.payment.util.applyColorDisable
import com.diegoferreiracaetano.payment.util.applyColorEnable
import com.diegoferreiracaetano.router.Router
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_payment.payment_btn_pay
import kotlinx.android.synthetic.main.fragment_payment.payment_img_mask
import kotlinx.android.synthetic.main.fragment_payment.payment_toolbar
import kotlinx.android.synthetic.main.fragment_payment.payment_txt_card
import kotlinx.android.synthetic.main.fragment_payment.payment_txt_edit
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

        payment_toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        val id = requireArguments().getLong(EXTRA_ID)
        viewModel.user(id).observe(this, Observer {
            it.onSuccess(::showPayment)
                .onFailure(::showError)
        })
    }

    override fun onStop() {
        super.onStop()
        payment_txt_value.hideKeyboard()
    }

    private fun showPayment(pair: Pair<Payment, Router>?) {
        pair?.apply {
            payment_img_mask.setImageUrl(first.user.img)
            payment_txt_username.text = first.user.name
            payment_txt_value.showKeyboard()
            payment_txt_value.afterTextChanged {
                it.removeMask().let {
                    first.value = it
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
            payment_txt_card.text = "${first.card.brand} ${first.card.number.toString().takeLast(4)}"
            payment_txt_edit.setOnClickListener {
                navigate(pair.second, first.user.id)
            }
            payment_btn_pay.setOnClickListener {
                viewModel.savePayment(this.first)
                    .observe(this@PaymentFragment, Observer {
                        it.onSuccess(::showTransaction).onFailure(::showError)
                    })
            }
        }
    }

    private fun showTransaction(pair: Pair<Long, Router?>) {
        if (pair.second != null) {
            navigate(pair.second!!, pair.first)
        } else
            Snackbar.make(requireView(), R.string.payment_fail, Snackbar.LENGTH_LONG).show()
    }

    private fun showError(throwable: Throwable) {
        Snackbar.make(requireView(), throwable.message.toString(), Snackbar.LENGTH_LONG).show()
    }

    companion object {
        private const val EXTRA_ID = "id"
    }
}
