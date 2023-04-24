package com.example.loginscreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.loginscreen.R
import com.example.loginscreen.`class`.User


class UserAdapter (val context: Context, var users : List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    lateinit var mListener : onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener : onItemClickListener){
       mListener = listener
    }

    class ViewHolder(view: View,listener: onItemClickListener) : RecyclerView.ViewHolder(view) {
        val tvnom: TextView
        val tvprenom: TextView
        val imgavatar : ImageView

        init {
            tvnom = view.findViewById(R.id.tvNom)
            tvprenom = view.findViewById(R.id.tvPrenom)
            imgavatar = view.findViewById(R.id.imgAvatar)

            view.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val rowView = LayoutInflater.from(viewGroup.context).inflate(R.layout.user_list_item, viewGroup, false)

        return ViewHolder(rowView,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // create a ProgressDrawable object which we will show as placeholder
        // create a ProgressDrawable object which we will show as placeholder
        val drawable = CircularProgressDrawable(context)
        drawable.setColorSchemeColors(
            R.color.purple_700,
            R.color.purple_200,
            R.color.purple_500
        )
        drawable.centerRadius = 30f
        drawable.strokeWidth = 5f
        // set all other properties as you would see fit and start it
        // set all other properties as you would see fit and start it
        drawable.start()

        Glide.with(holder.imgavatar.context)
            .load(users[position].avatar)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .placeholder(drawable)
            .into(holder.imgavatar);

        holder.tvnom.text = users[position].firstname
        holder.tvprenom.text = users[position].lastname
    }

    override fun getItemCount() = users.size

}