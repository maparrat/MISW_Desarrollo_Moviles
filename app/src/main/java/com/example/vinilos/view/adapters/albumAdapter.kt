package com.example.vinilos.view.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.model.AlbumModel
import com.example.vinilos.view.AlbumFragment
import com.example.vinilos.databinding.ActivityListAlbumBinding
import com.example.vinilos.view.DetailMainAlbumActivity


class AlbumsAdapter: RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    var albums: List<AlbumModel> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    class AlbumViewHolder(val viewDataBinding: ActivityListAlbumBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        val context = viewDataBinding.root.context

        companion object {
            @LayoutRes
            val LAYOUT = R.layout.activity_list_album
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: ActivityListAlbumBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }

        holder.viewDataBinding.root.setOnClickListener {


            val intent = Intent(holder.context, DetailMainAlbumActivity::class.java)
            intent.putExtra("albumId", albums[position].id)
            intent.putExtra("albumDescription", albums[position].description)
            intent.putExtra("albumName", albums[position].name)
            intent.putExtra("albumGenre", albums[position].genre)
            intent.putExtra("albumRecord", albums[position].recordLabel)
            intent.putExtra("releaseDate", albums[position].releaseDate)
            intent.putExtra("releaseCover", albums[position].cover)
            ContextCompat.startActivity(holder.context, intent, null)
        }

    }


    override fun getItemCount(): Int {
        return albums.size
    }




}