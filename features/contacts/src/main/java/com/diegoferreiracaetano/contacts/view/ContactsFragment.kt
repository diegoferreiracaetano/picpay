package com.diegoferreiracaetano.contacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diegoferreiracaetano.contacts.R
import com.diegoferreiracaetano.contacts.ResultData
import com.diegoferreiracaetano.contacts.succeeded
import com.diegoferreiracaetano.domain.user.User
import kotlinx.android.synthetic.main.fragment_contacts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment : Fragment() {

    val vm: ContactsViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.fetchContacts()
        setupAdapter()
    }

    private fun setupAdapter() {
        vm.contacts.observe(this, Observer {
            if(it.succeeded)
                showUser((it as ResultData.Success).data)
        })
    }

    private fun showUser(users: List<User>) {
        contact_recycle.adapter = ConstactsAdapter(users)
    }
}
