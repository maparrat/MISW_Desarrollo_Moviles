package com.example.vinilos.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vinilos.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonView = findViewById<Button>(R.id.button1)

        val buttonViewArtista = findViewById<Button>(R.id.button2)

        val buttonViewColeccionista= findViewById<Button>(R.id.button3)


        buttonView.setOnClickListener {
            val intent = Intent(this, ListMainAlbumActivity::class.java)
            startActivity(intent)
        }

        buttonViewArtista.setOnClickListener {
            val intent = Intent(this, ListMainArtistaActivity::class.java)
            startActivity(intent)
        }

        buttonViewColeccionista.setOnClickListener {
            val intent = Intent(this, ListMainColleccionistaActivity::class.java)
            startActivity(intent)
        }


    }
}