package com.example.vinilos.repositories


import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.model.MusicianModel
import com.example.vinilos.network.NetworkServiceAdapter


class musicianRepository (val application: Application) {

    fun refreshData(callback: (List<MusicianModel>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar.
        NetworkServiceAdapter.getInstance(application).getMusician({
            //Guardar los artistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}