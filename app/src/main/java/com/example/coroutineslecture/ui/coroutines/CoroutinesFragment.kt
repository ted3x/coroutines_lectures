package com.example.coroutineslecture.ui.coroutines

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
class CoroutinesFragment : Fragment() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    private lateinit var startButton: Button
    private lateinit var remainingTimeTextView: TextView
    private var job : Job? = null
    private var jobCounter: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragments_layout, container, false)

        remainingTimeTextView = view.findViewById(R.id.remainingTimeTextView)

        startButton = view.findViewById(R.id.startButton)
        
        startButton.setOnClickListener {

            val benchmarkTime = 5

            jobCounter = coroutineScope.launch {
                updateRemainingTime(benchmarkTime)
            }
            job = coroutineScope.launch {
                startButton.isEnabled = false
                val iterations = startBenchmark(benchmarkTime)
                Toast.makeText(requireContext(), iterations.toString(), Toast.LENGTH_LONG).show()
                startButton.isEnabled = true
            }
        }
        return view
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
        jobCounter?.cancel()
        jobCounter?.invokeOnCompletion {
            remainingTimeTextView.text = "done!"
        }
        job?.invokeOnCompletion {
            startButton.isEnabled = true
        }
    }

    private suspend fun startBenchmark(time: Int) : Int{
        return withContext(Dispatchers.Default) {
            ThreadInfoLogger.logThreadInfo("startBenchmark -> Started")
            val stopTime = System.nanoTime() + time * 1_000_000_000L

            var iterations = 0

            while (System.nanoTime() < stopTime) {
                iterations++
            }

            ThreadInfoLogger.logThreadInfo("startBenchmark -> completed")

            iterations
        }
    }

//    private suspend fun startBenchmark(time: Int) = withContext(Dispatchers.Default) {
//        ThreadInfoLogger.logThreadInfo("startBenchmark -> Started")
//        val stopTime = System.nanoTime() + time * 1_000_000_000L
//        var iterations = 0
//        while (System.nanoTime() < stopTime) {
//            iterations++
//        }
//        ThreadInfoLogger.logThreadInfo("startBenchmark -> completed")
//
//        iterations
//    }

    private suspend fun updateRemainingTime(remainingTime: Int) {
        for(time in remainingTime downTo 0){
            if (time > 0) {
                ThreadInfoLogger.logThreadInfo("Remaining time -> $time")
                remainingTimeTextView.text = "Remaining time $time"
                delay(1000)
            } else remainingTimeTextView.text = "complete"
        }
    }
}