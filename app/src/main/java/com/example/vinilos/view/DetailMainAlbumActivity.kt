/**
 * *
 * * Creado por : git@devlucasblandon
 * *
 */

package com.example.vinilos.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.vinilos.R
import com.example.vinilos.R.layout
import com.example.vinilos.model.TrackModel
import com.example.vinilos.viewmodels.AlbumViewModel
import com.example.vinilos.viewmodels.TrackViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class DetailMainAlbumActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var viewModel: TrackViewModel
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

        Log.d("id track enviado", id.toString())
        Log.d("id track enviado", descripcion.toString())

        if (id != null) {
            viewModel = ViewModelProvider(this, TrackViewModel.Factory(application, albumId = id.toInt()))[TrackViewModel::class.java]
        }


// Llama a getDataFromNetwork para cargar los datos
        viewModel.getDataFromNetwork()

        // Referencias a las vistas
        val formContainer = findViewById<LinearLayout>(R.id.form_container)
        val inputTrackName = findViewById<EditText>(R.id.input_track_name)
        val inputTrackDuration = findViewById<EditText>(R.id.input_track_duration)
        val buttonCreateTrack = findViewById<Button>(R.id.button_create_track)
        // Consultar el estado del Switch desde las SharedPreferences
        val sharedPreferences = getSharedPreferences("vinilos_prefs", MODE_PRIVATE)
        val isSwitchChecked = sharedPreferences.getBoolean("SWITCH_STATE", false)
        // Configurar la visibilidad del formulario basado en el estado del Switch
        if (isSwitchChecked) {
            formContainer.visibility = View.VISIBLE
        } else {
            formContainer.visibility = View.GONE
        }

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
        val releaseDate = release?.let { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(it) }
        val dateFormat = SimpleDateFormat("yyyy MMM dd  ", Locale.getDefault())
        release = dateFormat.format(releaseDate)
        textViewRelease.text = release
        val textViewImage = findViewById<ImageView>(R.id.albumCover)
        textViewImage.setImageResource(R.drawable.ic_launcher_background)



        buttonCreateTrack.setOnClickListener {
            val trackName = inputTrackName.text.toString()
            val trackDuration = inputTrackDuration.text.toString()

            if (trackName.isNotEmpty() && trackDuration.isNotEmpty()) {
                // Aquí puedes manejar la lógica para crear el track
                createTrack(id,trackName, trackDuration)
            } else {
                // Muestra un mensaje de error si los campos están vacíos
                showError("Please fill out all fields.")
            }
        }

        val buttonGetTrack = findViewById<Button>(R.id.button_get_track)

        buttonGetTrack.setOnClickListener {
            var trackstxt = ""
            viewModel.tracks.observe(this) { trackList ->
                trackList?.let { tracks ->
                    for (track in tracks) {
                        // Realiza la acción deseada con cada track
                        trackstxt = trackstxt + " / "+track.name
                        Log.d("Track agregado", "Nombre: ${track.name}")
                    }
                }
            }
            val textViewTracks = findViewById<TextView>(R.id.tracks)
            Log.d("tracks text", trackstxt)
            textViewTracks.text = trackstxt
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    private fun createTrack(id: String?, name: String, duration: String) {
        // Lógica para crear el track (enviar al servidor, guardar localmente, etc.)
        val track = id?.let { TrackModel(albumId = it.toInt(),name= name, duration= duration) }
        Log.d("id track enviado", id.toString())
        if (track != null) {

            viewModel.createTrack(track)
                // Si el track se crea correctamente, recarga la actividad
            recreate()

        }
    }

    private fun showError(message: String) {
        // Lógica para mostrar un error (puede ser un Toast o un diálogo)
    }
}

