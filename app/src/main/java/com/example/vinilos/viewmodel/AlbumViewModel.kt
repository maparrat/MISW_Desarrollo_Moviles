package com.example.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.AlbumModel
import com.example.vinilos.model.AlbumDetailModel
import com.example.vinilos.repositories.albumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AlbumViewModel(application: Application) :  AndroidViewModel(application) {

    private val albumsRepository = albumRepository(application)

    private val _albums = MutableLiveData<List<AlbumModel>>()
    private val _albumCreated = MutableLiveData<Boolean>()

    val albumCreated: LiveData<Boolean>
        get() = _albumCreated

    val albums: LiveData<List<AlbumModel>>
        get() = _albums

    private val _albumDetail = MutableLiveData<List<AlbumDetailModel>>()
    val albumDetail: LiveData<List<AlbumDetailModel>>
        get() = _albumDetail

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var data = albumsRepository.refreshData()
                    _albums.postValue(data)
                }
            }
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        }
        catch (e:Exception){
            Log.d("Error", e.toString())
            _eventNetworkError.value = true
        }

    }

    fun createAlbum(album: AlbumModel) {
        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.IO) {
                albumsRepository.createAlbum(album, { success ->
                    Log.d("Album enviado", "creado")
                    _albumCreated.value = success
                }, { error ->

                    _albumCreated.value = false
                })
            }
            _albumCreated.postValue(true)
        }
    }

    //Actualiza los detalles de un álbum obteniéndolos desde el repositorio y publica en los LiveData correspondientes.
    fun refreshDataDetailFromNetwork(albumId: Int) {
        albumsRepository.refreshDetailData(albumId, { albumDetail ->
            _albumDetail.postValue(albumDetail)
            _eventNetworkError.value = false
        }, {
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}