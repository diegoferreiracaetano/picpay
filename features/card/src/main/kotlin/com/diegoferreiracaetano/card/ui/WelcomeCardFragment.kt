package com.diegoferreiracaetano.card.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.diegoferreiracaetano.card.R
import com.diegoferreiracaetano.commons.navigate
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_welcome_card.card_btn_create
import kotlinx.android.synthetic.main.fragment_welcome_card.welcome_toolbar
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

        welcome_toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        val id = requireArguments().getLong(EXTRA_ID)

        viewModel.welcomeCard(id).observe(this, Observer {
            it.onSuccess {
                card_btn_create.navigate(it.second, it.first)
            }.onFailure(::showError)
        })
    }

    private fun showError(throwable: Throwable) {
        Snackbar.make(requireView(), throwable.message.toString(), Snackbar.LENGTH_LONG).show()
    }

    companion object {
        private const val EXTRA_ID = "id"
    }
}
