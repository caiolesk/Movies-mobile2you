package com.example.movies_mobile2you.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setupScroll(
    layoutManager: LinearLayoutManager,
    methodToInvokeAtEnd: () -> Unit
) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (checkIfIsTheListEnd(layoutManager, dy)) {
                methodToInvokeAtEnd()
            }
        }
    })
}

private fun checkIfIsTheListEnd(layoutManager: LinearLayoutManager, dy: Int): Boolean {
    with(layoutManager) {
        val visibleItemCount = childCount
        val totalItemCount = itemCount
        val firstVisibleItemPosition = findFirstVisibleItemPosition()
        return@checkIfIsTheListEnd visibleItemCount + firstVisibleItemPosition >= totalItemCount && dy > 0
    }
}
