package com.example.loginscreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.loginscreen.R
import com.example.loginscreen.`class`.User

class UserAdapter (val context: Context, val users : List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvnom: TextView
        val tvprenom: TextView
        val imgavatar : ImageView

        init {
            tvnom = view.findViewById(R.id.tvNom)
            tvprenom = view.findViewById(R.id.tvPrenom)
            imgavatar = view.findViewById(R.id.imgAvatar)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val rowView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.user_list_item, viewGroup, false)

        return ViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.imgavatar.context)
            .load(users[position].avatar)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(holder.imgavatar);

        holder.tvnom.text = users[position].firstname
        holder.tvprenom.text = users[position].lastname
    }

    override fun getItemCount() = users.size
}