package com.diegoferreiracaetano.card.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.diegoferreiracaetano.card.R
import com.diegoferreiracaetano.commons.navigate
import kotlinx.android.synthetic.main.fragment_welcome_card.card_btn_create
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeCardFragment : Fragment() {

    private val viewModel: CardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getLong(EXTRA_ID)

        viewModel.welcomeCard(id).observe(this, Observer {
            it.onSuccess {
                card_btn_create.navigate(it.second, it.first)
            }
        })
    }

    companion object {
        private const val EXTRA_ID = "id"
    }
}
