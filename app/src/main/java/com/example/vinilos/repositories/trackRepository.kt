package com.example.vinilos.repositories
import android.app.Application
import com.example.vinilos.model.TrackModel
import com.example.vinilos.network.NetworkServiceAdapter

class trackRepository (val application: Application) {
    suspend  fun refreshData(albumId:Int): List<TrackModel> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getTracks(albumId)
    }

}






