package com.example.coroutineslecture.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.coroutineslecture.R
import com.example.coroutineslecture.ui.background_thread.BackgroundThreadFragment
import com.example.coroutineslecture.ui.callbacks_example.CallbacksExampleFragment
import com.example.coroutineslecture.ui.coroutines.CoroutinesFragment
import com.example.coroutineslecture.ui.coroutines_async.CoroutinesAsyncFragment
import com.example.coroutineslecture.ui.coroutines_cancellation.CoroutinesCancellationFragment
import com.example.coroutineslecture.ui.main_thread.MainThreadFragment

class MainFragment : Fragment() {

    private lateinit var mainThreadButton : Button
    private lateinit var backgroundThreadButton : Button
    private lateinit var coroutinesButton : Button
    private lateinit var callbacksButton : Button
    private lateinit var asyncButton : Button
    private lateinit var cancellingCoroutines: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        mainThreadButton = view.findViewById(R.id.mainThreadButton)
        backgroundThreadButton = view.findViewById(R.id.backgroundThreadButton)
        coroutinesButton = view.findViewById(R.id.coroutinesButton)
        callbacksButton = view.findViewById(R.id.callbacksButton)
        asyncButton = view.findViewById(R.id.asyncButton)
        cancellingCoroutines = view.findViewById(R.id.cancelCoroutineButton)

        mainThreadButton.setOnClickListener {
            (activity as MainActivity).gotoFragment(MainThreadFragment())
        }

        backgroundThreadButton.setOnClickListener {
            (activity as MainActivity).gotoFragment(BackgroundThreadFragment())
        }

        coroutinesButton.setOnClickListener {
            (activity as MainActivity).gotoFragment(CoroutinesFragment())
        }

        callbacksButton.setOnClickListener {
            (activity as MainActivity).gotoFragment(CallbacksExampleFragment())
        }

        asyncButton.setOnClickListener {
            (activity as MainActivity).gotoFragment(CoroutinesAsyncFragment())
        }

        cancellingCoroutines.setOnClickListener {
            (activity as MainActivity).gotoFragment(CoroutinesCancellationFragment())
        }


        return view
    }
}