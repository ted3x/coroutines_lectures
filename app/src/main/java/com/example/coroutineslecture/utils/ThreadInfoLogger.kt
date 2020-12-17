package com.example.coroutineslecture.utils

import android.util.Log

object ThreadInfoLogger {
    private const val TAG = "ThreadInfoLogger"

    fun logThreadInfo(message: String) {
        Log.i(TAG, "$message; thread name: ${Thread.currentThread().name}; thread ID: ${Thread.currentThread().id}")
    }
}