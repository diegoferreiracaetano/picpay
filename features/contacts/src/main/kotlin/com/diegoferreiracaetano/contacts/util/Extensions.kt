package com.diegoferreiracaetano.contacts.util

import androidx.appcompat.widget.SearchView
import com.diegoferreiracaetano.contacts.R

fun SearchView.applyBackground() {
    setOnQueryTextFocusChangeListener { _, hasFocus ->

        setBackgroundResource(R.drawable.around_search)

        if (hasFocus)
            setBackgroundResource(R.drawable.around_search_selected)
    }
}
