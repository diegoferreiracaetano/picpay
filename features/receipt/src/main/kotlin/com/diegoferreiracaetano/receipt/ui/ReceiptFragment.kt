package com.diegoferreiracaetano.receipt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diegoferreiracaetano.commons.format
import com.diegoferreiracaetano.commons.formatCard
import com.diegoferreiracaetano.commons.setImageUrl
import com.diegoferreiracaetano.domain.receipt.Receipt
import com.diegoferreiracaetano.receipt.R
import kotlinx.android.synthetic.main.fragment_receipt.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DateFormat

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
        viewModel.receipt().observe(this, Observer {
            it.onSuccess(::showReceipt)
                .onFailure(::showError)
        })
    }

    private fun showReceipt(receipt: Receipt) {
        receipt_name.text = receipt.user.name
        receipt_image.setImageUrl(receipt.user.img)
        receipt_date.text = receipt.date.format(DateFormat.SHORT, DateFormat.SHORT)
        receipt_transaction.text = receipt.transaction.toString()
        receipt_card.text = receipt.card.number.formatCard()
        receipt_value.text = receipt.value.format()
        receipt_amount.text = receipt.total.format()
    }

    private fun showError(throwable: Throwable) {
    }

    companion object {
        private const val EXTRA_ID = "id"
    }
}
