package com.example.lab2_mobdev.fragments

import SearchAdapter
import User
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2_mobdev.R

class SearchFragment : Fragment() {

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private val userList = listOf(
        User("Alice", R.drawable.ic_profile_placeholder),
        User("Bob", R.drawable.ic_profile_placeholder),
        User("Charlie", R.drawable.ic_profile_placeholder),
        User("David", R.drawable.ic_profile_placeholder),
        User("Eve", R.drawable.ic_profile_placeholder)
        // Add more mock users if needed
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Initialize views
        searchEditText = view.findViewById(R.id.editTextSearch)
        recyclerView = view.findViewById(R.id.recyclerViewSearchResults)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        searchAdapter = SearchAdapter(userList)
        recyclerView.adapter = searchAdapter

        // Add text change listener for search input
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        return view
    }

    // Filter the list based on the search query
    private fun filter(query: String) {
        val filteredList = userList.filter {
            it.name.contains(query, ignoreCase = true)
        }
        searchAdapter.updateList(filteredList)
    }
}
