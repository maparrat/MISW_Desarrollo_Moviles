package com.example.vinilos.repositories
import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos.model.AlbumModel
import com.example.vinilos.model.TrackModel
import com.example.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class trackRepository (val application: Application) {
    suspend  fun refreshData(albumId:Int): List<TrackModel> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getTracks(albumId)
    }

   suspend fun getData(albumId: Int): List<TrackModel> {
        return NetworkServiceAdapter.getInstance(application).getTracks(albumId)
    }

    fun createTrack(
        track: TrackModel,
        onComplete: (Boolean) -> Unit,
        onError: (VolleyError) -> Unit
    ) {
        Log.d("createTrack", "Iniciando la creación del track")
        Log.d("createTrack", "Datos del track: Name=${track.name}, Duration=${track.duration}, AlbumId=${track.albumId}")

        val jsonObject = JSONObject()
        jsonObject.put("name", track.name)
        jsonObject.put("duration", track.duration)
        Log.d("createTrack", "JSON a enviar: $jsonObject")

        NetworkServiceAdapter.getInstance(application).postTrack(
            track.albumId,
            jsonObject,
            { response ->
                Log.d("createTrack", "Respuesta recibida: $response")
                onComplete(true)
            },
            { error ->
                Log.e("createTrack", "Error al crear el track: ${error.message}", error)
                onError(error)
            }
        )
    }


}






