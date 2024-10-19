package com.example.lab2_mobdev.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab2_mobdev.models.Post

class PostViewModel : ViewModel() {
    private val _posts = MutableLiveData<MutableList<Post>>(mutableListOf())
    val posts: LiveData<MutableList<Post>> get() = _posts

    // Add a new post to the list
    fun addPost(post: Post) {
        _posts.value?.add(post)
        _posts.value = _posts.value // Trigger LiveData update
    }
}
