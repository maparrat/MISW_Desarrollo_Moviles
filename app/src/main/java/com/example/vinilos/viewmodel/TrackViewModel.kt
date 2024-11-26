package com.example.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.AlbumModel
import com.example.vinilos.model.MusicianModel
import com.example.vinilos.model.TrackModel
import com.example.vinilos.repositories.musicianRepository
import com.example.vinilos.repositories.trackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TrackViewModel(application: Application, albumId: Int) :  AndroidViewModel(application) {

    private val tracksRepository = trackRepository(application)

    private val _tracks = MutableLiveData<List<TrackModel>>()
    private val _trackCreated = MutableLiveData<Boolean>()
    val tracks: LiveData<List<TrackModel>>
        get() = _tracks

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = albumId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = tracksRepository.refreshData(id)
                    _tracks.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    fun getDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val data = tracksRepository.refreshData(id) // Asume que devuelve una lista de TrackModel
                _tracks.postValue(data) // Publica los datos en LiveData
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        } catch (e: Exception) {
            _eventNetworkError.postValue(true)
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
    fun createTrack(track: TrackModel) {
        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.IO) {
                tracksRepository.createTrack(track, { success ->
                    Log.d("track enviado", "creado")
                    _trackCreated.value = success
                }, { error ->

                    _trackCreated.value = false
                })
            }
            _trackCreated.postValue(true)
        }
    }



    class Factory(val app: Application, val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TrackViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TrackViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}