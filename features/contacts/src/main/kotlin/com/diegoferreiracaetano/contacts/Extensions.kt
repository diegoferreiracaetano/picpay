package com.diegoferreiracaetano.contacts

import androidx.appcompat.widget.SearchView

fun SearchView.applyBackground() {
    setOnQueryTextFocusChangeListener { _, hasFocus ->

        setBackgroundResource(R.drawable.around_search)

        if (hasFocus)
            setBackgroundResource(R.drawable.around_search_selected)
    }
}
