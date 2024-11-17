/**
 * *
 * * Creado por : git@devlucasblandon
 * *
 */

package com.example.vinilos.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.vinilos.R
import com.example.vinilos.R.layout
import java.text.SimpleDateFormat
import java.util.Locale


class DetailMainArtistActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_detail_artista)

        val bundle = intent.extras
        Log.d(bundle?.getString("musicianGenre"), "bundle ver g")

        var id = bundle?.getString("musicianId")
        var descripcion = bundle?.getString("musicianDescription")
        var name = bundle?.getString("musicianName")
        var image = bundle?.getString("musicianImage")
        var release = bundle?.getString("musicianDate")

        val textView = findViewById<TextView>(R.id.artistName)
        textView.text = id
        val textViewd = findViewById<TextView>(R.id.artistDescription)
        textViewd.text = descripcion
        val textViewName = findViewById<TextView>(R.id.artistName)
        textViewName.text = name
        val textViewImage = findViewById<ImageView>(R.id.artistImage)
        //textViewImage.setImageResource(R.drawable.ic_launcher_foreground)
        textViewImage.setImageResource(R.drawable.ic_launcher_background)
        //val artistImageView: ImageView = findViewById(R.id.artistImage)
        //artistImageView.setImageResource(R.drawable.ic_launcher_background)
        val textViewRelease = findViewById<TextView>(R.id.artistDate)
        textViewRelease.text = release
        val releaseDate = release?.let { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(it) }
        val dateFormat = SimpleDateFormat("yyyy MMM dd  ", Locale.getDefault())
        release = dateFormat.format(releaseDate)
        textViewRelease.text = release

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

