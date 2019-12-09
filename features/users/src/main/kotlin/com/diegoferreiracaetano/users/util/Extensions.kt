package com.diegoferreiracaetano.users.util

import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.router.Router
import com.diegoferreiracaetano.users.R
import com.facebook.shimmer.ShimmerFrameLayout

fun SearchView.applyBackground() {
    setOnQueryTextFocusChangeListener { _, hasFocus ->

        setBackgroundResource(R.drawable.around_search)

        if (hasFocus)
            setBackgroundResource(R.drawable.around_search_selected)
    }
}

fun View.loading(boolean: Boolean) {
    val simmer = (context as AppCompatActivity).findViewById<ShimmerFrameLayout>(R.id.user_loading)
    if(boolean) simmer.visible(true) else simmer.visible(false)
}

fun ShimmerFrameLayout.visible(boolean: Boolean) {
    if(boolean) {
        visibility = View.VISIBLE
        startShimmer()
    } else {
        visibility = View.GONE
        stopShimmer()
    }
}