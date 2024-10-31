package com.example.vinilos.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.model.AlbumModel
import com.example.vinilos.view.AlbumFragment
import com.example.vinilos.databinding.ActivityListAlbumBinding


class AlbumsAdapter: RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    var albums: List<AlbumModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
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
            val album = holder.viewDataBinding.album // Acceder a los datos asociados a la vista

            Toast.makeText(holder.context, "Has seleccionado: ${albums[position]} ", Toast.LENGTH_SHORT).show()
        }
//        holder.viewDataBinding.root.setOnClickListener {
//            val action = AlbumFragmentDirections.actionAlbumFragmentToCommentFragment(albums[position].albumId)
//            // Navigate using that action
//            holder.viewDataBinding.root.findNavController().navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }




}