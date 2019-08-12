package com.diegoferreiracaetano.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_contacts.view.contact_image
import kotlinx.android.synthetic.main.item_contacts.view.contact_txt_name
import kotlinx.android.synthetic.main.item_contacts.view.contact_txt_nickname

internal class ConstactsAdapter(
    private var items: List<ContactsFragment.User>
): RecyclerView.Adapter<ConstactsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contacts, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = items[position]
        holder.image.setImageResource(R.drawable.contact)
        holder.nickname.text = user.name
        holder.name.text = user.nickname
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.contact_image
        val nickname = view.contact_txt_nickname
        val name = view.contact_txt_name
    }
}