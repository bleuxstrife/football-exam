package com.dicoding.exam.footballexam.utils

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

open class CouroutineContextProvider {
    open val main: CoroutineContext by lazy { UI }
}