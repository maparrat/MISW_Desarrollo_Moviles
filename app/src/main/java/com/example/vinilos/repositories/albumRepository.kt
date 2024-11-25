package com.example.vinilos.repositories
import android.app.Application
import androidx.compose.ui.window.application
import com.android.volley.VolleyError
import com.example.vinilos.model.AlbumModel
import com.example.vinilos.model.AlbumDetailModel
import com.example.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject


class albumRepository (val application: Application) {
    suspend  fun refreshData(): List<AlbumModel> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }

    fun createAlbum(album: AlbumModel, onComplete: (Boolean) -> Unit, onError: (VolleyError) -> Unit) {
        val jsonObject = JSONObject()
        jsonObject.put("name", album.name)
        jsonObject.put("cover", album.cover)
        jsonObject.put("releaseDate", album.releaseDate)
        jsonObject.put("description", album.description)
        jsonObject.put("genre", album.genre)
        jsonObject.put("recordLabel", album.recordLabel)

        NetworkServiceAdapter.getInstance(application).postAlbum(jsonObject, { response ->
            onComplete(true)
        }, { error ->
            onError(error)
        })
    }


    fun refreshDetailData(
        albumId: Int,
        callback: (List<AlbumDetailModel>) -> Unit,
        onError: (VolleyError) -> Unit
    ) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código


        NetworkServiceAdapter.getInstance(application).getAlbumDetails(
            albumId, {
                //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
                    albumDetail ->
                callback(albumDetail)
            },
            onError
        )


    }
}






