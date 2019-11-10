package com.diegoferreiracaetano.payment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.diegoferreiracaetano.commons.Router
import com.diegoferreiracaetano.commons.removeMask
import com.diegoferreiracaetano.commons.setImageUrl
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.payment.R
import com.diegoferreiracaetano.payment.util.afterTextChanged
import com.diegoferreiracaetano.payment.util.applyColorDisable
import com.diegoferreiracaetano.payment.util.applyColorEnable
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
        viewModel.fetchContact(id).observeForever {
            it.onSuccess(::showUser)
                .onFailure(::showError)
        }
    }

    private fun showUser(user: User) {
        payment_img_mask.setImageUrl(user.img)
        payment_txt_username.text = user.username
        payment_txt_card.text = "mastercard 1234"
        payment_txt_value.afterTextChanged {
            it.removeMask().toFloat().let {
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
        payment_btn_pay.setOnClickListener {
            val url = Router(it.context).Receipt().next(user.id)
            it.findNavController().navigate(url)
        }
    }

    private fun showError(throwable: Throwable) {
    }

    companion object {
        private const val EXTRA_ID = "id"
    }
}
