package com.example.coroutineslecture.ui.coroutines_async

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coroutineslecture.R
import com.example.coroutineslecture.utils.ThreadInfoLogger
import kotlinx.coroutines.*

@SuppressLint("SetTextI18n")
class CoroutinesAsyncFragment : Fragment() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    private lateinit var startButton: Button
    private lateinit var resultsTextView: TextView
    private var job : Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_async_layout, container, false)

        resultsTextView = view.findViewById(R.id.resultTextView)

        startButton = view.findViewById(R.id.startButton)
        
        startButton.setOnClickListener {
            job = coroutineScope.launch {
                startButton.isEnabled = false
                resultsTextView.text = ""
                val firstResult = async { getFirstResult() }
                val secondResult = async { getSecondResult() }
                resultsTextView.text = "${firstResult.await()} + ${secondResult.await()}"
                startButton.isEnabled = true
            }
        }
        return view
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
        job?.invokeOnCompletion {
            startButton.isEnabled = true
        }
    }

    private suspend fun getFirstResult() : String{
        ThreadInfoLogger.logThreadInfo("getFirstResult - > Start")
        delay(3000L)
        val message = "first result"
        ThreadInfoLogger.logThreadInfo("getFirstResult - > Complete")
        return message
    }

    private suspend fun getSecondResult() : String{
        ThreadInfoLogger.logThreadInfo("getSecondResult - > Start")
        delay(1000L)
        val message = "second result"
        ThreadInfoLogger.logThreadInfo("getSecondResult - > Complete")
        return message
    }
}