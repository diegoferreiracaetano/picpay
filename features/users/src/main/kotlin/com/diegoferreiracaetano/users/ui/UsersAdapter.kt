package com.diegoferreiracaetano.users.ui
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.commons.setImageUrl
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.users.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_users.view.user_img_user
import kotlinx.android.synthetic.main.item_users.view.user_txt_name
import kotlinx.android.synthetic.main.item_users.view.user_txt_nickname

internal class UsersAdapter(
    private var items: List<User>
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var onItemClick: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_users, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = items[position]
        holder.image.setImageUrl(user.img)
        holder.nickname.text = user.name
        holder.name.text = user.username
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(user)
        }
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: CircleImageView = view.user_img_user
        val nickname: TextView = view.user_txt_nickname
        val name: TextView = view.user_txt_name
    }
}
