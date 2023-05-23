package com.example.networkdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.networkdemo.databinding.ActivityAddPostBinding
import com.example.networkdemo.network.NetHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPostActivity : AppCompatActivity() {

    companion object {
        const val TAG = "AddPostActivity"
    }

    private lateinit var binding: ActivityAddPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddPostBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val post = NetHandler.postsApi.create(
                    Post(
                        title = binding.editTextTextPersonName.text.toString(),
                        body = binding.editTextTextPersonName2.text.toString(),
                        userId = 1
                    )
                )

                Log.i(TAG, "Post created with id: ${post.id}")

                finish()
            }
        }
    }
}