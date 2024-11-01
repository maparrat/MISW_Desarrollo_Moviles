package com.example.vinilos.network

import android.content.ClipData.Item
import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos.model.AlbumModel
import com.example.vinilos.model.AlbumDetailModel
import com.example.vinilos.model.Tracks
import com.example.vinilos.model.Performers
import com.example.vinilos.model.Comments
import org.json.JSONArray
import org.json.JSONObject

class NetworkServiceAdapter(context: Context) {

    companion object{
        const val BASE_URL= "https://vinilos-164ec7207f32.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getAlbums( onComplete:(resp:List<AlbumModel>)->Unit , onError: (error: VolleyError)->Unit){
        requestQueue.add(getRequest("albums",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<AlbumModel>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, AlbumModel(id = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description")))
                }
                onComplete(list)
            },
            {
                onError(it)
            }))
    }

    fun getAlbumDetails( albumId: Int, onComplete:(resp:List<AlbumDetailModel>)->Unit , onError: (error: VolleyError)->Unit){
        requestQueue.add(getRequest("albums/$albumId",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<AlbumDetailModel>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, AlbumDetailModel(
                        id = item.getInt("id"),
                        tracks = parseTracks(item.getJSONArray("performers")),
                        performers = parsePerformers(item.getJSONArray("performers")),
                        comments = parseComments(item.getJSONArray("comments"))
                    ))
                }
                onComplete(list)
            },
            {
                onError(it)
            }))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }

    private fun parseTracks(jsonArray: JSONArray): Array<Tracks> {
        val tracks = mutableListOf<Tracks>()
        for (i in 0 until jsonArray.length()) {
            val trackItem = jsonArray.getJSONObject(i)
            tracks.add(
                Tracks(
                    id = trackItem.getInt("id"),
                    name = trackItem.getString("name"),
                    duration = trackItem.getString("duration")
                )
            )
        }
        return tracks.toTypedArray()
    }

    private fun parsePerformers(jsonArray: JSONArray): Array<Performers> {
        val performers = mutableListOf<Performers>()
        for (i in 0 until jsonArray.length()) {
            val performerItem = jsonArray.getJSONObject(i)
            performers.add(
                Performers(
                    id = performerItem.getInt("id"),
                    name = performerItem.getString("name"),
                    image = performerItem.getString("image"),
                    description = performerItem.getString("description"),
                    birthDate = performerItem.getString("birthDate")
                )
            )
        }
        return performers.toTypedArray()
    }

    private fun parseComments(jsonArray: JSONArray): Array<Comments> {
        val comments = mutableListOf<Comments>()
        for (i in 0 until jsonArray.length()) {
            val commentItem = jsonArray.getJSONObject(i)
            comments.add(
                Comments(
                    id = commentItem.getInt("id"),
                    description = commentItem.getString("description"),
                    rating = commentItem.getInt("rating")
                )
            )
        }
        return comments.toTypedArray()
    }


}