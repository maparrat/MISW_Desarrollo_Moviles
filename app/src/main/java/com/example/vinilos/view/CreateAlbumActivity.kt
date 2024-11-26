package com.example.vinilos.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.R
import com.example.vinilos.model.AlbumModel
import com.example.vinilos.viewmodels.AlbumViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class CreateAlbumActivity : AppCompatActivity() {
    private lateinit var viewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_album) // Asegúrate de usar R.layout.activity_create_album

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar el ViewModel
        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)

        // Observar los LiveData del ViewModel
        observeViewModel()

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
            // Validar que los campos no estén vacíos
            if (inputName.text.isNullOrEmpty() ||
                inputCover.text.isNullOrEmpty() ||
                inputReleaseDate.text.isNullOrEmpty() ||
                inputDescription.text.isNullOrEmpty() ||
                inputGenre.text.isNullOrEmpty() ||
                inputRecordLabel.text.isNullOrEmpty()
            ) {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Obtener la fecha ingresada por el usuario
            val inputDateStr = inputReleaseDate.text.toString()

            // Definir los formatos de fecha de entrada y salida
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())

            // Establecer la zona horaria a GMT-05:00
            outputFormat.timeZone = TimeZone.getTimeZone("GMT-05:00")

            try {
                // Parsear la fecha de entrada
                val date: Date = inputFormat.parse(inputDateStr)

                // Formatear la fecha al formato de salida deseado
                val formattedDate: String = outputFormat.format(date)

                // Crear el objeto AlbumModel con la fecha formateada
                val album = AlbumModel(
                    id = 0, // id es null porque aún no ha sido asignado
                    name = inputName.text.toString(),
                    cover = inputCover.text.toString(),
                    releaseDate = formattedDate,
                    description = inputDescription.text.toString(),
                    genre = inputGenre.text.toString(),
                    recordLabel = inputRecordLabel.text.toString()
                )

                // Enviar el álbum utilizando tu método existente
                sendAlbum(album)

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Formato de fecha inválido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.albumCreated.observe(this, Observer { album ->
            // Álbum creado exitosamente
            Toast.makeText(this, "Álbum creado: ", Toast.LENGTH_SHORT).show()

            // Navegar a ListMainAlbumActivity
            val intent = Intent(this, ListMainAlbumActivity::class.java)
            // Opcionalmente, puedes agregar flags para limpiar el back stack
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish() // Finalizar la actividad actual
        })


    }

    private fun sendAlbum(album: AlbumModel) {
        // Lógica para enviar el objeto al servidor
        viewModel.createAlbum(album)
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
