package com.example.vinilos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vinilos.model.AlbumModel
import com.example.vinilos.model.AlbumProvider

class AlbumViewModel: ViewModel() {
    val albumModel = MutableLiveData<AlbumModel>()
    //TODO: Retornar lista de albunes llamando al API MODIFICANDO albums
    fun getAlbums() {
        val curretAlbum: AlbumModel = AlbumProvider.getAlbum()
        albumModel.postValue(curretAlbum)
    }
}