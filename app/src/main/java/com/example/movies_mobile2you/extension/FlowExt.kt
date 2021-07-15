package com.example.movies_mobile2you.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun <T> Flow<T>.onCollect(
    onLoading: ((Boolean) -> Unit)? = null,
    onSuccess: ((T) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
    coroutineScope: CoroutineScope = CoroutineScope(IO)
) {
    coroutineScope.launch {
        withContext(Main) {
            onLoading?.invoke(true)
        }
        try {
            collect { value ->
                withContext(Main) {
                    onSuccess?.invoke(value)
                }
            }
        } catch (e: Exception) {
            onError?.invoke(e)
        } finally {
            withContext(Main) {
                onLoading?.invoke(false)
            }
        }
    }
}