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


class DetailMainCollectorActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_detail_coleccionista)

        val bundle = intent.extras
        Log.d(bundle?.getString("collectorId"), "bundle ver g")

        var name = bundle?.getString("collectorName")
        var telephone = bundle?.getString("collectorTelephone")
        var email = bundle?.getString("collectorEmail")


        val textViewName = findViewById<TextView>(R.id.collectorName)
        textViewName.text = name
        val textViewTelephone = findViewById<TextView>(R.id.collectorTelephone)
        textViewTelephone.text = telephone
        val textViewEmail = findViewById<TextView>(R.id.collectorEmail)
        textViewEmail.text = email

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

