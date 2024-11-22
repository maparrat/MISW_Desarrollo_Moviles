



package com.example.vinilos.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.vinilos.R
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.vinilos.R.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CreateAlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_create_album)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        val inputName = findViewById<EditText>(R.id.input_name)
        val inputCover = findViewById<EditText>(R.id.input_cover)
        val inputReleaseDate = findViewById<EditText>(R.id.input_release_date)
        val inputDescription = findViewById<EditText>(R.id.input_description)
        val inputGenre = findViewById<EditText>(R.id.input_genre)
        val inputRecordLabel = findViewById<EditText>(R.id.input_record_label)
        val buttonSubmit = findViewById<Button>(R.id.button_submit)


        // Configurar el selector de fecha
        inputReleaseDate.setOnClickListener {
            showDatePicker(inputReleaseDate)
        }

        buttonSubmit.setOnClickListener {
            val album = JSONObject().apply {
                put("name", inputName.text.toString())
                put("cover", inputCover.text.toString())
                put("releaseDate", inputReleaseDate.text.toString())
                put("description", inputDescription.text.toString())
                put("genre", inputGenre.text.toString())
                put("recordLabel", inputRecordLabel.text.toString())
            }

            // Aquí puedes enviar el objeto JSON usando una librería como Retrofit o Volley
            sendAlbum(album)

        }

    }
    private fun sendAlbum(album: JSONObject) {
        // Lógica para enviar el objeto a un servidor
    }
    private fun showDatePicker(editText: EditText) {
        // Obtener la fecha actual
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Crear y mostrar el DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Formatear y mostrar la fecha seleccionada
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)

                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                editText.setText(dateFormat.format(selectedDate.time))
            },
            year, month, day
        )

        datePickerDialog.show()
    }
}
