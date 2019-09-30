package com.diegoferreiracaetano.payment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.isDigitsOnly
import com.diegoferreiracaetano.commons.moneyMask
import com.diegoferreiracaetano.commons.removeMask
import com.diegoferreiracaetano.commons.setImageUrl
import kotlinx.android.synthetic.main.fragment_blank.*
import org.koin.ext.isFloat

/**
 * A simple [Fragment] subclass.
 */
class PaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mask.setImageUrl("https://randomuser.me/api/portraits/men/35.jpg")
        username.text = "@username"
        card.text = "mastercard 1234"
        value.afterTextChanged {
            it.removeMask().toFloat().let {
                if (it > 0) {
                    value.applyColorEnable()
                    real.applyColorEnable()
                    pay.applyColorEnable()
                } else {
                    value.applyColorDisable()
                    real.applyColorDisable()
                    pay.applyColorDisable()
                }
            }
        }
    }
}
