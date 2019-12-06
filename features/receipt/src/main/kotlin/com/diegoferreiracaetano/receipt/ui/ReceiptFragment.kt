package com.diegoferreiracaetano.receipt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.diegoferreiracaetano.commons.format
import com.diegoferreiracaetano.commons.formatCard
import com.diegoferreiracaetano.commons.setImageUrl
import com.diegoferreiracaetano.domain.transaction.Transaction
import com.diegoferreiracaetano.receipt.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import java.text.DateFormat
import kotlinx.android.synthetic.main.fragment_receipt.receipt_amount
import kotlinx.android.synthetic.main.fragment_receipt.receipt_card
import kotlinx.android.synthetic.main.fragment_receipt.receipt_date
import kotlinx.android.synthetic.main.fragment_receipt.receipt_image
import kotlinx.android.synthetic.main.fragment_receipt.receipt_name
import kotlinx.android.synthetic.main.fragment_receipt.receipt_transaction
import kotlinx.android.synthetic.main.fragment_receipt.receipt_value
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiptFragment : BottomSheetDialogFragment() {

    private val viewModel: ReceiptViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_receipt, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            DialogFragment.STYLE_NORMAL,
            R.style.AppBottomSheetDialogTheme
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getLong(EXTRA_ID)
        viewModel.transaction(id).observe(this, Observer {
            it.onSuccess(::showPayment)
                .onFailure(::showError)
        })
    }

    private fun showPayment(transaction: Transaction) {
        receipt_name.text = transaction.user.name
        receipt_image.setImageUrl(transaction.user.img)
        receipt_date.text = transaction.date.format(DateFormat.SHORT, DateFormat.SHORT)
        receipt_transaction.text = transaction.id.toString()
        receipt_value.text = transaction.value.format()
        receipt_amount.text = transaction.value.format()
        transaction.card?.let {
            receipt_card.text = "Cartão ${it.brand} ${it.number.toString().takeLast(4)}"
        }
    }

    private fun showError(throwable: Throwable) {
        Snackbar.make(requireView(), throwable.message.toString(), Snackbar.LENGTH_LONG).show()
    }

    companion object {
        private const val EXTRA_ID = "id"
    }
}
