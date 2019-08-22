package com.diegoferreiracaetano.contacts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.contacts.R
import com.diegoferreiracaetano.contacts.setImageUrl
import com.diegoferreiracaetano.domain.user.User
import kotlinx.android.synthetic.main.item_contacts.view.*

internal class ConstactsAdapter(
    private var items: List<User>
): RecyclerView.Adapter<ConstactsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contacts, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = items[position]
        holder.image.setImageUrl(user.img)
        holder.nickname.text = user.name
        holder.name.text = user.username
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.contact_image
        val nickname = view.contact_txt_nickname
        val name = view.contact_txt_name
    }
}