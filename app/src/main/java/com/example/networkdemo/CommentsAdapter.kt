package com.example.networkdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentsAdapter(val list: List<Comment>) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView
        private val bodyTextView: TextView
        private lateinit var comment: Comment
        init {
            titleTextView = itemView.findViewById(android.R.id.text1)
            bodyTextView = itemView.findViewById(android.R.id.text2)
        }

        fun setComment(p: Comment) {
            comment = p
            titleTextView.text = "${p.name} (${p.email})"
            bodyTextView.text = comment.body
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
        holder.setComment(list[position])
    }

}