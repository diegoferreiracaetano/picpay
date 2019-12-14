package com.diegoferreiracaetano.users.ui

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.commons.SnackbarMessage
import com.diegoferreiracaetano.commons.navigate
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.router.Router
import com.diegoferreiracaetano.users.R
import com.diegoferreiracaetano.users.util.loading
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.Snackbar

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("show_adapter")
    private fun RecyclerView.showSuccess(result: Pair<List<User>, Router>?) {
        result?.let {
            val usersAdapter = UsersAdapter(it.first)
            adapter = usersAdapter
            usersAdapter.onItemClick = {user->
                navigate(it.second, user.id)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("show_loading")
    private fun RecyclerView.showLoading(result: Boolean) {
        val simmer = (context as AppCompatActivity).findViewById<ShimmerFrameLayout>(R.id.user_loading)

        if(result){
            visibility = GONE
            simmer.loading(result)
        }else{
            visibility = VISIBLE
            simmer.loading(result)
        }
    }

    @JvmStatic
    @BindingAdapter("show_error")
    private fun View.showError(snackbarMessage: SnackbarMessage?) {
        snackbarMessage?.let {
            Snackbar.make(this, snackbarMessage.messageId, snackbarMessage.duration()).show()
        }
    }
}                                