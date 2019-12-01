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
import androidx.work.Operation
import androidx.work.WorkInfo
import androidx.work.WorkInfo.State
import com.diegoferreiracaetano.commons.navigate
import com.diegoferreiracaetano.contacts.R
import com.diegoferreiracaetano.contacts.util.applyBackground
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.router.Router
import com.google.android.material.snackbar.Snackbar
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

        val router = requireArguments().getString(EXTRA_ROUTER)
        if(!router.isNullOrEmpty()) {
            requireArguments().clear()
            navigate(router)
        }
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


        viewModel.job.observe(this, Observer {
            when(it.state) {
                State.RUNNING -> startShimmer()
                State.FAILED -> showError()
                else -> stopShimmer()
            }
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

    private fun showUser(pair: Pair<List<User>, Router>) {
        stopShimmer()
        contactsAdapter = ContactsAdapter(pair.first)
        contact_recycle.adapter = contactsAdapter
        contactsAdapter.onItemClick = {
            navigate(pair.second, it.id)
        }
    }


    private fun showError() {
        stopShimmer()
        Snackbar.make(requireView(), R.string.contacts_msg_error, Snackbar.LENGTH_LONG).show()
    }

    private fun showError(error: Throwable) {
        stopShimmer()
        contact_error.visibility = VISIBLE
        contact_container.visibility = GONE
        contact_error.retry(View.OnClickListener {
            startShimmer()
            setupAdapter()
        })
        Timber.e(error)
    }

    companion object {
        private const val EXTRA_ROUTER = "router"
    }
}
