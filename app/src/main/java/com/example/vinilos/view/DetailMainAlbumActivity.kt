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
        //Log.d(bundle.toString(), "bundle ver")

        val b = bundle?.getInt("albumId")
        var a = bundle?.getString("albumId")
         a = "tonces "

        val textView = findViewById<TextView>(R.id.albumTitle)
        textView.text = a
        //Toast.makeText(this, a, Toast.LENGTH_SHORT).show()

        //val  a  = intent.getStringExtra("albumId")
        //Toast.makeText(this, a, Toast.LENGTH_SHORT).show()


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

