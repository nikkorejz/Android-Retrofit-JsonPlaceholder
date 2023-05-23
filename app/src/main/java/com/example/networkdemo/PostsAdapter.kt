package com.example.networkdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostsAdapter(val list: List<Post>, val listener: PostsInteraction) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView
        private val bodyTextView: TextView
        private lateinit var post: Post
        init {
            titleTextView = itemView.findViewById(android.R.id.text1)
            bodyTextView = itemView.findViewById(android.R.id.text2)
            itemView.setOnClickListener {
                listener.onPostChosen(post)
            }
        }

        fun setPost(p: Post) {
            post = p
            titleTextView.text = post.title
            bodyTextView.text = post.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setPost(list[position])
    }

}