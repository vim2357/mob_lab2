package com.example.lab2_mobdev.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.findNavController
import com.example.lab2_mobdev.R
import com.example.lab2_mobdev.models.Post

class PostAdapter(private val postList: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.textUsername.text = post.username
        holder.textCaption.text = post.caption
        holder.textLikes.text = "${post.likes} likes"
        holder.imagePost.setImageResource(post.imageResource)

        // Click listener for username (profile navigation)
        holder.textUsername.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("username", post.username)
            holder.itemView.findNavController().navigate(R.id.profileFragment, bundle)
        }

        // Click listener for liking a post
        holder.textLikes.setOnClickListener {
            // Increment the like count for the post
            post.likes += 1
            holder.textLikes.text = "${post.likes} likes"
        }

        // Click listener for commenting (display a simple Toast for now)
        holder.textCaption.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Commenting on ${post.username}'s post", Toast.LENGTH_SHORT).show()
        }
    }


    override fun getItemCount(): Int = postList.size

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textUsername: TextView = itemView.findViewById(R.id.textUsername)
        val textCaption: TextView = itemView.findViewById(R.id.textCaption)
        val textLikes: TextView = itemView.findViewById(R.id.textLikes)
        val imagePost: ImageView = itemView.findViewById(R.id.imagePost)
    }
}
