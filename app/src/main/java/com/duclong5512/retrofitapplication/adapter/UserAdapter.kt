package com.duclong5512.retrofitapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duclong5512.retrofitapplication.R
import com.duclong5512.retrofitapplication.model.UserGetData
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(private val listener: ItemClickListener) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var users = emptyList<UserGetData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = users[position]
        holder.itemView.tvID.text = currentUser.id.toString()
        holder.itemView.tvName.text = currentUser.name
        holder.itemView.tvEmail.text = currentUser.email
        holder.itemView.tvGender.text = currentUser.gender
        holder.itemView.tvStatus.text = currentUser.status
    }

    override fun getItemCount() = users.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<UserGetData>) {
        this.users = users
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val itemClickListener = listener

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            itemClickListener.onClick(v, adapterPosition)
        }
    }

    interface ItemClickListener {
        fun onClick(v: View, position: Int)
    }
}