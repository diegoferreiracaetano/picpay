package com.diegoferreiracaetano.users.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.GONE
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.work.WorkInfo.State
import com.diegoferreiracaetano.commons.navigate
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.router.Router
import com.diegoferreiracaetano.users.R
import com.diegoferreiracaetano.users.databinding.FragmentUsersBinding
import com.diegoferreiracaetano.users.util.applyBackground
import com.diegoferreiracaetano.users.util.loading
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_users.searchView
import kotlinx.android.synthetic.main.fragment_users.user_container
import kotlinx.android.synthetic.main.fragment_users_loading.shimmer_view_container
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.lang.Exception

class UsersFragment : Fragment() {

    private val viewModel: UsersViewModel by viewModel()
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var binding: FragmentUsersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        binding.lifecycleOwner = this@UsersFragment
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchView()
        view.loading(true)

        val id = requireArguments().getLong(EXTRA_ID)

        if (id.toInt() != 0) {
            requireArguments().clear()
            viewModel.receipt(id).observe(this, Observer {
                it.onSuccess {
                    navigate(it.second, it.first)
                }.onFailure{
                    showError()
                }
            })
        }
    }

    override fun onStop() {
        super.onStop()
        requireView().loading(false)
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


    private fun showError() {
        Snackbar.make(requireView(), R.string.users_msg_error, Snackbar.LENGTH_LONG).show()
    }

    companion object {
        private const val EXTRA_ID = "id"
    }
}
