package com.example.vinilos.repositories


import android.app.Application
import android.content.Context
import com.android.volley.VolleyError
import com.example.vinilos.model.MusicianModel
import com.example.vinilos.network.NetworkServiceAdapter
import com.google.gson.Gson


class musicianRepository(val application: Application) {

    private val sharedPreferences = application.getSharedPreferences("musician_cache", Context.MODE_PRIVATE)

    fun refreshData(callback: (List<MusicianModel>) -> Unit, onError: (VolleyError) -> Unit) {
        // Primero intenta obtener datos de la caché
        val cachedData = sharedPreferences.getString("musicians", null)

        if (cachedData != null) {
            // Si hay datos en caché, los parseamos y devolvemos
            val musicians = Gson().fromJson(cachedData, Array<MusicianModel>::class.java).toList()
            callback(musicians)
        } else {
            // Si no hay datos en caché, los obtenemos de la red
            NetworkServiceAdapter.getInstance(application).getMusician({
                // Guardar en caché para futuras solicitudes
                sharedPreferences.edit().putString("musicians", Gson().toJson(it)).apply()
                callback(it)
            }, onError)
        }
    }
}


