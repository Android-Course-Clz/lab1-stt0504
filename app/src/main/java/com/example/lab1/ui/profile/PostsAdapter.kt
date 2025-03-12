package com.example.lab1.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab1.R
import com.example.lab1.databinding.ItemPostBinding

class PostsAdapter : ListAdapter<Post, PostsAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.postTextView.text = post.text
            binding.likeCountTextView.text = post.likes.toString()
            binding.commentCountTextView.text = post.comments.toString()

            if (post.imageUrl != null) {
                binding.postImageView.visibility = View.VISIBLE
                Glide.with(binding.root.context)
                    .load(post.imageUrl)
                    .into(binding.postImageView)
            } else {
                binding.postImageView.visibility = View.GONE
            }

            var isLiked = false

            fun updateLikeButton() {
                val imageRes = if (isLiked) R.drawable.ic_like_filled else R.drawable.ic_like
                binding.likeButton.setImageDrawable(ContextCompat.getDrawable(binding.root.context, imageRes))
                binding.likeCountTextView.text = post.likes.toString()
            }

            updateLikeButton()

            binding.likeButton.setOnClickListener {
                isLiked = !isLiked
                post.likes += if (isLiked) 1 else -1
                updateLikeButton()
            }

            binding.commentButton.setOnClickListener {
            }
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem.text == newItem.text
        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
    }
}
