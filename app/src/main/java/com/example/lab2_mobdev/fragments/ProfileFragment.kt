package com.example.lab2_mobdev.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2_mobdev.R
import com.example.lab2_mobdev.adapters.PostAdapter
import com.example.lab2_mobdev.viewmodels.PostViewModel

class ProfileFragment : Fragment() {

    private lateinit var usernameTextView: TextView
    private lateinit var bioTextView: TextView
    private lateinit var profileImageView: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private val postViewModel: PostViewModel by activityViewModels() // Shared ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the fragment_profile layout
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize views
        usernameTextView = view.findViewById(R.id.textViewUsername)
        bioTextView = view.findViewById(R.id.textViewBio)
        profileImageView = view.findViewById(R.id.imageProfilePicture)
        recyclerView = view.findViewById(R.id.recyclerViewUserPosts)

        // Retrieve username from arguments
        val username = arguments?.getString("username") ?: "User 1"
        usernameTextView.text = username

        // Set a mock bio and profile picture for demonstration
        bioTextView.text = "This is the bio of $username. Welcome to the profile page!"
        profileImageView.setImageResource(R.drawable.ic_profile_placeholder)

        // Set up RecyclerView for displaying user posts
        recyclerView.layoutManager = GridLayoutManager(context, 3)

        // Observe the posts from the ViewModel
        postViewModel.posts.observe(viewLifecycleOwner) { posts ->
            // Filter posts to show only the posts by the current user
            val userPosts = posts.filter { it.username == username }
            postAdapter = PostAdapter(userPosts)
            recyclerView.adapter = postAdapter
        }

        return view
    }
}
