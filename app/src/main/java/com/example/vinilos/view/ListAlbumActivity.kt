package com.example.vinilos.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vinilos.R
import androidx.lifecycle.Observer
import com.example.vinilos.databinding.ActivityListAlbumBinding
import com.example.vinilos.viewmodel.AlbumViewModel

class ListAlbumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListAlbumBinding
    private val albumViewModel: AlbumViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListAlbumBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        //TODO: ACONDICIONAR LA RESPUESTA DE ALBUNES A LA VISTA
        albumViewModel.albumModel.observe(this,Observer { currentAlbum ->
        binding.textView.text = currentAlbum.name
        })
        albumViewModel.getAlbums()
    }
}