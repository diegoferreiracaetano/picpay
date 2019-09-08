package com.diegoferreiracaetano.contacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView.GONE
import androidx.appcompat.widget.SearchView.VISIBLE
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diegoferreiracaetano.contacts.R
import com.diegoferreiracaetano.contacts.applyBackground
import com.diegoferreiracaetano.domain.user.User
import kotlinx.android.synthetic.main.fragment_contacts.contact_recycle
import kotlinx.android.synthetic.main.fragment_contacts.scroll_contacts
import kotlinx.android.synthetic.main.fragment_contacts.searchView
import kotlinx.android.synthetic.main.fragment_contacts_loading.shimmer_view_container
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment : Fragment() {

    private val viewModel: ContactsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startShimmer()
        setupAdapter()
        setupSearchView()
    }

    override fun onStop() {
        super.onStop()
        stopShimmer()
    }

    private fun setupSearchView() {
        searchView.applyBackground()
    }

    private fun setupAdapter() {
        viewModel.contacts.observe(this, Observer {
            it.onSuccess(::showUser)
                .onFailure(::showError)
        })
    }

    private fun startShimmer() {
        shimmer_view_container.startShimmer()
        viewModel.fetchContacts()
    }

    private fun stopShimmer() {
        shimmer_view_container.visibility = GONE
        shimmer_view_container.stopShimmer()
    }

    private fun showUser(users: List<User>) {
        stopShimmer()
        contact_recycle.adapter = ConstactsAdapter(users)
    }

    private fun showError(error: Throwable) {
        stopShimmer()
        Toast.makeText(requireContext(), R.string.contacts_msg_error, Toast.LENGTH_LONG).show()
    }
}
