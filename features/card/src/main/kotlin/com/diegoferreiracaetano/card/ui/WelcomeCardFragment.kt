package com.diegoferreiracaetano.card.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.diegoferreiracaetano.card.R
import com.diegoferreiracaetano.commons.Router
import kotlinx.android.synthetic.main.fragment_welcome_card.card_btn_create

class WelcomeCardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireArguments().getInt(EXTRA_ID)
        card_btn_create.setOnClickListener {
            val url = Router(it.context).Card().welcome(id)
            it.findNavController().navigate(url)
        }
    }

    companion object {
        private const val EXTRA_ID = "id"
    }
}
