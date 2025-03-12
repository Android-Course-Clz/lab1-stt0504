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
            .load("https://flomaster.top/uploads/posts/2023-01/thumbs/1673563853_flomaster-club-p-profil-risunok-vkontakte-35.jpg")
            .circleCrop()
            .into(binding.profileImageView)

        binding.profileNameTextView.text = "Имя пользователя"
        binding.profileNicknameTextView.text = "@nickname"

        var isSubscribed = false
        var subscribersCount = 100

        fun updateSubscribeButton() {
            if (isSubscribed) {
                binding.subscribeButton.text = "Вы подписаны"
                binding.subscribeButton.setTextColor(ContextCompat.getColor(this, R.color.gray))
            } else {
                binding.subscribeButton.text = "Подписаться"
                binding.subscribeButton.setTextColor(ContextCompat.getColor(this, R.color.blue))
            }
            binding.profileStatsTextView.text = "Подписчики: $subscribersCount • Подписки: 50 • Посты: 3"
        }

        updateSubscribeButton()

        binding.subscribeButton.setOnClickListener {
            isSubscribed = !isSubscribed
            if (isSubscribed) subscribersCount++ else subscribersCount--
            updateSubscribeButton()
        }

        binding.messageButton.setOnClickListener {
        }
    }

    private fun setupRecyclerView() {
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.postsRecyclerView.adapter = postsAdapter

        postsAdapter.submitList(
            listOf(
                Post("Первый пост", "https://i.pinimg.com/736x/01/db/f1/01dbf1cdaad1d95096dfae5b4af01d6f.jpg", 10, 2),
                Post("Второй пост", "https://i.pinimg.com/736x/af/f5/a1/aff5a10fa331303b306c227a454530c7.jpg", 5, 1),
                Post("Третий пост", "https://i.pinimg.com/736x/3a/06/f1/3a06f1101dec6506956519ad2bff2521.jpg", 15, 5)
            )
        )
    }
}
