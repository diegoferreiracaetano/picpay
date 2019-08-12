package com.diegoferreiracaetano.contacts

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_contacts.contact_recycle

class ContactsFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {

        val list = listOf(
            User("", "Diego 1", "Nickname"),
            User("", "Diego 2", "Nickname"),
            User("", "Diego 3", "Nickname"))

        contact_recycle.adapter = ConstactsAdapter(list)
    }

    class User(
        val image: String,
        val name: String,
        val nickname: String
    )
}
