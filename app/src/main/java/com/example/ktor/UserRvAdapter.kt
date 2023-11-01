package com.example.retrofitdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ktor.Data
import com.example.ktor.R
import com.squareup.picasso.Picasso

class UsersRvAdapter(private val list: List<Data>) : RecyclerView.Adapter<UsersRvAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_single_user, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvUserName.text = list[position].first_name
        Picasso.get().load(list[position].avatar).into(holder.ivUserAvatar)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivUserAvatar: ImageView
        val tvUserName: TextView
        init {
            ivUserAvatar = itemView.findViewById(R.id.ivUserAvatar)
            tvUserName = itemView.findViewById(R.id.tvUserName)
        }
    }
}