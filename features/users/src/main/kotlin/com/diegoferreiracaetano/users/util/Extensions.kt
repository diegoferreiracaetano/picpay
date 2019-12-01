package com.diegoferreiracaetano.users.util

import androidx.appcompat.widget.SearchView
import com.diegoferreiracaetano.users.R

fun SearchView.applyBackground() {
    setOnQueryTextFocusChangeListener { _, hasFocus ->

        setBackgroundResource(R.drawable.around_search)

        if (hasFocus)
            setBackgroundResource(R.drawable.around_search_selected)
    }
}
