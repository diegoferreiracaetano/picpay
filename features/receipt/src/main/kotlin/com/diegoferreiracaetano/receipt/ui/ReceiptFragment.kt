package com.diegoferreiracaetano.receipt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diegoferreiracaetano.commons.setImageUrl
import com.diegoferreiracaetano.domain.receipt.Receipt
import com.diegoferreiracaetano.receipt.R
import kotlinx.android.synthetic.main.fragment_receipt.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiptFragment : Fragment() {

    private val viewModel: ReceiptViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_receipt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(EXTRA_ID)
        viewModel.receipt().observeForever {
            it.onSuccess(::showReceipt)
                .onFailure(::showError)
        }
    }

    private fun showReceipt(receipt: Receipt) {
        receipt_name.text = receipt.user.name
        receipt_image.setImageUrl(receipt.user.img)
        receipt_date.text = receipt.date.toString()
        receipt_transaction.text = receipt.transaction.toString()
        receipt_card.text = receipt.card.number.toString()
        receipt_value.text = receipt.value.toString()
        receipt_amount.text = receipt.total.toString()
    }

    private fun showError(throwable: Throwable) {
    }

    companion object {
        private const val EXTRA_ID = "id"
    }
}
