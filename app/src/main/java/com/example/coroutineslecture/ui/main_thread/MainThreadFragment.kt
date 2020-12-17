package com.example.coroutineslecture.ui.main_thread

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coroutineslecture.R
import com.example.coroutineslecture.utils.ThreadInfoLogger

@SuppressLint("SetTextI18n")

class MainThreadFragment : Fragment() {

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

        ThreadInfoLogger.logThreadInfo("startBenchmark - > Started")

        updateRemainingTime(time)
        val stopTime = System.nanoTime() + time * 1_000_000_000L
        var iterations = 0


        while (System.nanoTime() < stopTime) {
            iterations++
        }

        Toast.makeText(requireContext(), iterations.toString(), Toast.LENGTH_LONG).show()
        ThreadInfoLogger.logThreadInfo("startBenchmark -> completed")
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