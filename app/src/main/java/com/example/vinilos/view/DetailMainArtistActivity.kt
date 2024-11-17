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


class DetailMainArtistActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_detail_artista)

        val bundle = intent.extras
        Log.d(bundle?.getString("musicianGenre"), "bundle ver g")

        var id = bundle?.getString("musicianId")
        var descripcion = bundle?.getString("musicianDescription")
        var name = bundle?.getString("musicianName")

        var genre = bundle?.getString("musicianGenre")

        var release = bundle?.getString("musicianDate")




        val textView = findViewById<TextView>(R.id.artistName)
        textView.text = id
        val textViewd = findViewById<TextView>(R.id.artistDescription)
        textViewd.text = descripcion
        val textViewName = findViewById<TextView>(R.id.artistName)
        textViewName.text = name

        val textViewGenre = findViewById<TextView>(R.id.artistGenre)
        textViewGenre.text = genre
        val textViewRelease = findViewById<TextView>(R.id.artistDate)
        textViewRelease.text = release




    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

