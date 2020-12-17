package com.example.coroutineslecture.ui.coroutines_cancellation

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
class CoroutinesCancellationFragment : Fragment() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    private lateinit var startButton: Button
    private lateinit var resultsTextView: TextView
    private var job1 : Job? = null
    private var job2 : Job? = null
    private var job3 : Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_async_layout, container, false)

        resultsTextView = view.findViewById(R.id.resultTextView)

        startButton = view.findViewById(R.id.startButton)
        
        startButton.setOnClickListener {

            job1 = coroutineScope.launch {
                while(true){
                    ThreadInfoLogger.logThreadInfo("job1 coroutine works")
                }
            }

//            job2 = coroutineScope.launch {
//                while(isActive){
//                    ThreadInfoLogger.logThreadInfo("job2 coroutine works")
//                }
//            }
//
//            job3 = coroutineScope.launch {
//                while(true){
//                    delay(1000L)
//                    ThreadInfoLogger.logThreadInfo("job3 coroutine works")
//                }
//            }
        }
        return view
    }

    override fun onStop() {
        super.onStop()
        job1?.cancel()
//        job2?.cancel()
//        job3?.cancel()
    }
}