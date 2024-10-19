package com.example.lab2_mobdev.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.lab2_mobdev.R
import com.example.lab2_mobdev.models.Post
import com.example.lab2_mobdev.viewmodels.PostViewModel

class AddPostFragment : Fragment() {

    private lateinit var postImageView: ImageView
    private lateinit var captionEditText: EditText
    private lateinit var postButton: Button
    private val postViewModel: PostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_post, container, false)

        postImageView = view.findViewById(R.id.imageViewPost)
        captionEditText = view.findViewById(R.id.editTextCaption)
        postButton = view.findViewById(R.id.buttonPost)

        postButton.setOnClickListener {
            val caption = captionEditText.text.toString()
            if (caption.isNotEmpty()) {
                // Create a new Post object
                val newPost = Post(
                    username = "User 1",
                    caption = caption,
                    imageResource = R.drawable.ic_post_placeholder,
                    likes = 0
                )

                postViewModel.addPost(newPost)

                captionEditText.text.clear()
                Toast.makeText(context, "Post submitted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please enter a caption", Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }
}
