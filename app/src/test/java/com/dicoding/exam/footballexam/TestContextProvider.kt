package com.dicoding.exam.footballexam

import com.dicoding.exam.footballexam.utils.CouroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider : CouroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}