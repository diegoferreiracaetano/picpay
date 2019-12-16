package com.diegoferreiracaetano

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import com.diegoferreiracaetano.domain.ResultRouter
import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.flow
import org.hamcrest.Description
import org.hamcrest.Matcher

internal object Mock {

    fun user() = User(
        0,
        "User",
        "@username"
    )

    fun users() = listOf(user())

    fun card() = Card(
        id = 1,
        brand = "MASTERCARD",
        number = 11111111111111,
        name = "DIEGO CAETANO",
        date = "11/11",
        cvv = 111
    )
}

internal fun <T> T.toResultSuccessTest(router: Router) = flow {
    emit(Result.success(ResultRouter.add(this@toResultSuccessTest, router)))
}

internal fun <T> T.toLiveDataResultTest(): LiveData<Result<T>> {
    val result = Result.success(this)
    return liveData {
        emit(result)
    }
}

internal fun <T> T.toLiveDataTest(): LiveData<T> {
    return liveData {
        emit(this@toLiveDataTest)
    }
}

internal fun <T> T.toLiveDataResultTest(router: Router): LiveData<Result<ResultRouter<T>>> {
    val result = Result.success(ResultRouter.add(this, router))
    return liveData {
        emit(result)
    }
}

fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View?>? {
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(item: RecyclerView): Boolean {
            val viewHolder = item.findViewHolderForAdapterPosition(position) ?: return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }
}
