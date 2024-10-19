package com.example.lab2_mobdev.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2_mobdev.R
import com.example.lab2_mobdev.adapters.PostAdapter
import com.example.lab2_mobdev.viewmodels.PostViewModel

class HomeFeedFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private val postViewModel: PostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_feed, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewPosts)
        recyclerView.layoutManager = LinearLayoutManager(context)

        postViewModel.posts.observe(viewLifecycleOwner) { posts ->
            postAdapter = PostAdapter(posts)
            recyclerView.adapter = postAdapter
        }

        return view
    }
}
