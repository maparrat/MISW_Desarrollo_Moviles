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
import com.example.vinilos.databinding.ActivityListArtistaBinding
import com.example.vinilos.model.MusicianModel
//import com.example.vinilos.view.MusicianFragment
import com.example.vinilos.databinding.ActivityMainListArtistaBinding
import com.example.vinilos.view.DetailMainArtistActivity

//import com.example.vinilos.view.DetailMainMusicianActivity


class MusiciansAdapter: RecyclerView.Adapter<MusiciansAdapter.MusicianViewHolder>() {

    var musicians: List<MusicianModel> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MusicianViewHolder(val viewDataBinding: ActivityListArtistaBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        val context = viewDataBinding.root.context

        companion object {
            @LayoutRes
            val LAYOUT = R.layout.activity_list_artista
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicianViewHolder {
        val withDataBinding: ActivityListArtistaBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            MusicianViewHolder.LAYOUT,
            parent,
            false)
        return MusicianViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: MusicianViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.musician = musicians[position]
        }
        holder.viewDataBinding.root.setOnClickListener {


            val intent = Intent(holder.context, DetailMainArtistActivity::class.java)
            intent.putExtra("musicianId", musicians[position].id)
            intent.putExtra("musicianDescription", musicians[position].description)
            intent.putExtra("musicianName", musicians[position].name)
            intent.putExtra("musicianImage", musicians[position].image)
            intent.putExtra("musicianDate", musicians[position].birthDate)

            ContextCompat.startActivity(holder.context, intent, null)

            //val musician = holder.viewDataBinding.musician // Acceder a los datos asociados a la vista
            //Toast.makeText(holder.context, "Has seleccionado: ${musicians[position]} ", Toast.LENGTH_SHORT).show()
        }

    }


    override fun getItemCount(): Int {
        return musicians.size
    }




}