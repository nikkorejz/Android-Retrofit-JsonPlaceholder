package com.example.networkdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkdemo.databinding.ActivityMainBinding
import com.example.networkdemo.network.NetHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentsActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CommentsActivity"
        const val PARAM_POST_ID = "PostId"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.root.layoutManager = LinearLayoutManager(this)

        val postId = intent.extras?.getString(PARAM_POST_ID, "") ?: ""

        Log.i(TAG, "Post id: $postId")

        CoroutineScope(Dispatchers.IO).launch {
            val comments = NetHandler.postsApi.getComments(postId)

//            comments.forEach {
//                Log.i(TAG, it.body)
//            }

            runOnUiThread {
                binding.root.adapter = CommentsAdapter(comments as List<Comment>)
            }
        }

    }
}