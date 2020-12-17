package com.example.coroutineslecture.ui.callbacks_example

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
import kotlinx.coroutines.delay

class CallbacksExampleFragment : Fragment() {

    private lateinit var counterTextView: TextView
    private lateinit var counterButton: Button
    private var counter = 0
    val token = Token()
    val post = Post()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_callbacks_example, container, false)

        counterTextView = view.findViewById(R.id.counterTextView)
        counterButton = view.findViewById(R.id.counterButton)

        counterButton.setOnClickListener {
            counter++
            counterTextView.text = counter.toString()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //postItem(Item("test title"))
    }

//    private fun requestToken() : Token{
//        // makes request for token
//        //while waiting for result, Thread is blocked
//        return token // returns token
//    }
//    private fun createPost(token: Token, item: Item) : Post{
//        // sends item to server and waits
//        return post // returns post
//    }
//    private fun processPost(post: Post){
//        // we do some local process of result
//    }
//
//    private fun postItem(item: Item){
//        val token = requestToken()
//        val post = createPost(token, item)
//        processPost(post)
//    }

//    private fun requestToken(callback : (Token) ->Unit){
//        ThreadInfoLogger.logThreadInfo("requestToken -> started")
//        Thread {
//            // we imitate network request and delay it for 3 Second
//            //invokes callback when done
//            Thread.sleep(3000L)
//            callback(Token(1))
//
//            ThreadInfoLogger.logThreadInfo("requestToken -> finished")
//        }.start()
//
//        // it returns immediately
//    }
//
//    private fun createPost(token: Token, item: Item, callback : (Post) ->Unit){
//        ThreadInfoLogger.logThreadInfo("createPost -> started")
//        Thread{
//            // we send Item to server and wait for response as Post
//            // we again imitate network request and delay it for 3 seconds
//            Thread.sleep(3000L)
//            callback(Post(1))
//
//            ThreadInfoLogger.logThreadInfo("createPost -> finished")
//        }.start()
//    }
//
//    private fun processPost(post: Post){
//        ThreadInfoLogger.logThreadInfo("processPost -> finished")
//        Handler(Looper.getMainLooper()).post {
//            counterTextView.text = post.userName
//        }
//    }
//
//    private fun postItem(item: Item){
//        requestToken{ token ->
//            createPost(token, item){ post ->
//                processPost(post)
//            }
//        }
//    }

//    private suspend fun requestToken() : Token{
//        delay(3000)
//        // makes request for token
//        //while waiting for result, Thread is blocked
//        return token // returns token
//    }
//    private suspend fun createPost(token: Token, item: Item) : Post{
//        delay(3000)
//        // sends item to server and waits
//        return post // returns post
//    }
//    private fun processPost(post: Post){
//        // we do some local process of result
//    }
//
//    private suspend fun postItem(item: Item){
//        val token = requestToken()
//        val post = createPost(token, item)
//        processPost(post)
//    }
}

