package com.diegoferreiracaetano.contacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.GONE
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diegoferreiracaetano.commons.navigate
import com.diegoferreiracaetano.commons.unaccent
import com.diegoferreiracaetano.contacts.R
import com.diegoferreiracaetano.contacts.util.applyBackground
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.router.Router
import kotlinx.android.synthetic.main.fragment_contacts.contact_container
import kotlinx.android.synthetic.main.fragment_contacts.contact_error
import kotlinx.android.synthetic.main.fragment_contacts.contact_recycle
import kotlinx.android.synthetic.main.fragment_contacts.searchView
import kotlinx.android.synthetic.main.fragment_contacts_loading.shimmer_view_container
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ContactsFragment : Fragment() {

    private val viewModel: ContactsViewModel by viewModel()
    private lateinit var contactsAdapter: ContactsAdapter

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
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return true
            }
        })
    }

    private fun setupAdapter() {
        viewModel.users().observe(this, Observer {
            it.onSuccess(::showUser)
                .onFailure(::showError)
        })
    }

    private fun startShimmer() {
        contact_error.visibility = GONE
        contact_container.visibility = VISIBLE
        shimmer_view_container.startShimmer()
    }

    private fun stopShimmer() {
        shimmer_view_container.visibility = GONE
        shimmer_view_container.stopShimmer()
    }

    private fun showUser(users: List<User>) {
        stopShimmer()
        showAdapter(users)
        filter(users)
    }

    private fun showAdapter(users: List<User>) {
        contactsAdapter = ContactsAdapter(users)
        contactsAdapter.onItemClick = {
            viewModel.save(it).observe(this, Observer {
                it.onSuccess {
                    navigate(it.second, it.first)
                }.onFailure (::showError)
            })
        }
        contact_recycle.adapter = contactsAdapter
    }

    private fun filter(users: List<User>) {
        viewModel.search.observe(this, Observer {
            val filter = users.filter { user -> user.name.unaccent().contains(it, ignoreCase = true) }
            showAdapter(filter)
        })
    }

    private fun showError(error: Throwable) {
        stopShimmer()
        contact_error.visibility = VISIBLE
        contact_container.visibility = GONE
        contact_error.retry(View.OnClickListener {
            startShimmer()
        })
        Timber.e(error)
    }
}
