package com.example.ktor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktor.databinding.ActivityMain2Binding
import com.example.retrofitdemo.adapter.UsersRvAdapter
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    private var _binding: ActivityMain2Binding? = null
    private val binding
        get() = _binding!!

    private lateinit var usersRvAdapter: UsersRvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = KtorManager().ktorClient
        val userApiUtil = ApiUtil(client)

        //! recycler view
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                val result = userApiUtil.getAllUsers()
                showAllUsers(result)
            }
        }
    }

    private fun showAllUsers(list: List<Data>?) {
        if (list?.isNotEmpty()!!) {
            binding.usersRecyclerView.visibility = View.VISIBLE
            binding.tvNoRecords.visibility = View.GONE
            usersRvAdapter = UsersRvAdapter(list)
            binding.usersRecyclerView.adapter = usersRvAdapter
        } else {
            binding.usersRecyclerView.visibility = View.GONE
            binding.tvNoRecords.visibility = View.VISIBLE
        }
    }
}