package com.diegoferreiracaetano.users.ui

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.work.WorkInfo
import com.diegoferreiracaetano.commons.navigate
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.router.Router
import com.diegoferreiracaetano.users.R
import com.diegoferreiracaetano.users.util.loading
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_users.user_recycle


object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("show_adapter")
    fun RecyclerView.setReviewAdapter(result : Result<Pair<List<User>, Router>>?) {
        if(result != null) {
            result.fold({ value ->
                showSuccess(value)
            }, { error ->
               showError(error)
            })
        }
    }

    @JvmStatic
    @BindingAdapter("show_job")
    fun View.setJob(work : WorkInfo?) {
        work?.let {
            when (it.state) {
                WorkInfo.State.RUNNING -> loading(true)
                WorkInfo.State.FAILED -> {
                    showError(Throwable(context.getString(R.string.users_msg_error)))
                }
                else -> loading(false)
            }
        }
    }

    private fun RecyclerView.showSuccess(result: Pair<List<User>, Router>) {
        val usersAdapter = UsersAdapter(result.first)
        adapter = usersAdapter
        usersAdapter.onItemClick = {user->
            navigate(result.second, user.id)
        }
    }

    private fun View.showError(t: Throwable) {
        loading(false)
        Snackbar.make(this, t.message.toString(), Snackbar.LENGTH_LONG).show()
    }
}                                