package com.diegoferreiracaetano.users.ui

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
import com.diegoferreiracaetano.domain.ResultRouter
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.users.R
import com.diegoferreiracaetano.users.util.applyBackground
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_users.searchView
import kotlinx.android.synthetic.main.fragment_users.user_container
import kotlinx.android.synthetic.main.fragment_users.user_recycle
import kotlinx.android.synthetic.main.fragment_users_loading.shimmer_view_container
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {

    private val viewModel: UsersViewModel by viewModel()
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startShimmer()
        setupUser()
        setupJob()
        setupSearchView()

        val id = requireArguments().getLong(EXTRA_ID, 0)

        if (id.toInt() != 0) {
            requireArguments().clear()
            viewModel.receipt(id).observe(this, Observer {
                it.onSuccess {
                    navigate(it.second, it.first)
                }.onFailure { showError() }
            })
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

    private fun setupUser() {
        viewModel.users().observe(this, Observer {
            it.onSuccess(::showUser)
                .onFailure { showError() }
        })
    }

    private fun setupJob() {
        viewModel.job().observe(this, Observer {
            it.onSuccess(::showJob)
                .onFailure { showError() }
        })
    }

    private fun startShimmer() {
        user_container.visibility = VISIBLE
        shimmer_view_container.startShimmer()
    }

    private fun stopShimmer() {
        shimmer_view_container.visibility = GONE
        shimmer_view_container.stopShimmer()
    }

    private fun showUser(result: ResultRouter<List<User>>) {
        stopShimmer()
        usersAdapter = UsersAdapter(result.result)
        user_recycle.adapter = usersAdapter
        usersAdapter.onItemClick = {
            navigate(result.router, it.id)
        }
    }

    private fun showJob(boolean: Boolean) {
        if (boolean)
            stopShimmer()
    }

    private fun showError() {
        stopShimmer()
        Snackbar.make(requireView(), R.string.users_msg_error, Snackbar.LENGTH_LONG).show()
    }

    companion object {
        private const val EXTRA_ID = "id"
    }
}
