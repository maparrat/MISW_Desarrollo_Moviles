/**
 * *
 * * Creado por : git@devlucasblandon
 * *
 */

package com.example.vinilos.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.vinilos.R
import com.example.vinilos.R.layout


class DetailMainAlbumActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_detail_album)

        val bundle = intent.extras
        Log.d(bundle?.getString("albumGenre"), "bundle ver g")

        var id = bundle?.getString("albumId")
        var descripcion = bundle?.getString("albumDescription")
        var name = bundle?.getString("albumName")
        var record = bundle?.getString("albumRecord")
        var genre = bundle?.getString("albumGenre")
        var release = bundle?.getString("releaseDate")
        var releaseCover = bundle?.getString("releaseCover")



        val textView = findViewById<TextView>(R.id.albumTitle)
        textView.text = id
        val textViewd = findViewById<TextView>(R.id.albumDescription)
        textViewd.text = descripcion
        val textViewName = findViewById<TextView>(R.id.albumName)
        textViewName.text = name
        val textViewRecord = findViewById<TextView>(R.id.albumRecord)
        textViewRecord.text = record
        val textViewGenre = findViewById<TextView>(R.id.albumGenre)
        textViewGenre.text = genre
        val textViewRelease = findViewById<TextView>(R.id.albumRelease)
        textViewRelease.text = release
        val textViewCover = findViewById<TextView>(R.id.albumCover)
        textViewCover.text = releaseCover


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

