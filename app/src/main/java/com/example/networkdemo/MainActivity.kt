package com.example.networkdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkdemo.databinding.ActivityMainBinding
import com.example.networkdemo.network.NetHandler
import com.example.networkdemo.network.PostsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), PostsInteraction {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.root.layoutManager = LinearLayoutManager(this)

//        val retrofit = with(Retrofit.Builder()) {
//            baseUrl("https://jsonplaceholder.typicode.com")
//            addConverterFactory(GsonConverterFactory.create())
//            build()
//        }

//        val postsApi = retrofit.create(PostsApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val posts = NetHandler.postsApi.getAll()

//                posts.forEach {
//                    Log.i(TAG, it.title)
//                }

                runOnUiThread {
                    binding.root.adapter = PostsAdapter(posts as List<Post>, this@MainActivity)
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

//        postsApi.getAllSync().enqueue(object : Callback<Collection<Post>> {
//            override fun onResponse(
//                call: Call<Collection<Post>>,
//                response: Response<Collection<Post>>
//            ) {
//                response.body()?.forEach {
//                    Log.i(TAG, it.title)
//                }
//            }
//
//            override fun onFailure(call: Call<Collection<Post>>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//
//        })
    }

    override fun onPostChosen(post: Post) {
        val intent = Intent(this, CommentsActivity::class.java)
        intent.putExtra(CommentsActivity.PARAM_POST_ID, post.id.toString())
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ItemAdd -> {
                startActivity(Intent(this, AddPostActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}