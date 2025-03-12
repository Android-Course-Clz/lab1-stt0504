package com.example.lab1.ui.profile

import androidx.core.content.ContextCompat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.lab1.R
import com.example.lab1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val postsAdapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupProfile()
        setupRecyclerView()
    }

    private fun setupProfile() {
        Glide.with(this)
            .load(getString(R.string.profile_image_url))
            .circleCrop()
            .into(binding.profileImageView)

        binding.profileNameTextView.text = getString(R.string.profile_name)
        binding.profileNicknameTextView.text = getString(R.string.profile_nickname)

        var isSubscribed = false
        var subscribersCount = resources.getInteger(R.integer.default_subscribers)

        fun updateSubscribeButton() {
            binding.subscribeButton.text = getString(
                if (isSubscribed) R.string.subscribed else R.string.subscribe
            )
            binding.subscribeButton.setTextColor(
                ContextCompat.getColor(this, if (isSubscribed) R.color.gray else R.color.blue)
            )
            binding.profileStatsTextView.text = getString(
                R.string.profile_stats,
                subscribersCount,
                resources.getInteger(R.integer.default_following),
                resources.getInteger(R.integer.default_posts)
            )
        }

        updateSubscribeButton()

        binding.subscribeButton.setOnClickListener {
            isSubscribed = !isSubscribed
            subscribersCount += if (isSubscribed) 1 else -1
            updateSubscribeButton()
        }

        binding.messageButton.setOnClickListener {}
    }


    private fun setupRecyclerView() {
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.postsRecyclerView.adapter = postsAdapter

        val posts = resources.getStringArray(R.array.post_texts)
        val images = resources.getStringArray(R.array.post_images)
        val likes = resources.getIntArray(R.array.post_likes)
        val comments = resources.getIntArray(R.array.post_comments)

        val postList = posts.indices.map { index ->
            Post(posts[index], images.getOrNull(index), likes[index], comments[index])
        }

        postsAdapter.submitList(postList)
    }

}
