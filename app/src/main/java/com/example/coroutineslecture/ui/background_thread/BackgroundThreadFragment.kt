package com.example.coroutineslecture.ui.background_thread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.coroutineslecture.R
import com.example.coroutineslecture.utils.ThreadInfoLogger

class BackgroundThreadFragment : Fragment() {

    private lateinit var startButton: Button
    private lateinit var remainingTimeTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragments_layout, container, false)

        remainingTimeTextView = view.findViewById(R.id.remainingTimeTextView)

        startButton = view.findViewById(R.id.startButton)
        startButton.setOnClickListener {
            startBenchmark()
        }
        return view
    }

    private fun startBenchmark() {
        val time = 5

        updateRemainingTime(time)

        ThreadInfoLogger.logThreadInfo("startBenchmark -> Started")

        Thread{
            val stopTime = System.nanoTime() + time * 1_000_000_000L
            var iterations = 0

            while (System.nanoTime() < stopTime) {
                iterations++
            }

            Handler(Looper.getMainLooper()).post {
                Toast.makeText(requireContext(), iterations.toString(), Toast.LENGTH_LONG).show()
            }

            ThreadInfoLogger.logThreadInfo("startBenchmark -> completed")
        }.start()
    }

    private fun updateRemainingTime(remainingTime: Int) {
        ThreadInfoLogger.logThreadInfo("Remaining time -> $remainingTime")
        if (remainingTime > 0) {
            remainingTimeTextView.text = "Remaining time $remainingTime"
            Handler(Looper.getMainLooper()).postDelayed({
                updateRemainingTime(remainingTime - 1)
            }, 1000)
        }
        else remainingTimeTextView.text = "complete"
    }
}