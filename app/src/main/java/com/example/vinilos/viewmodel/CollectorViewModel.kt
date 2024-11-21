package com.example.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.CollectorModel
import com.example.vinilos.model.CollectorDetailModel
import com.example.vinilos.repositories.collectorRepository


class CollectorViewModel(application: Application) :  AndroidViewModel(application) {

    private val collectorsRepository = collectorRepository(application)

    private val _collectors = MutableLiveData<List<CollectorModel>>()

    val collectors: MutableLiveData<List<CollectorModel>>
        get() = _collectors

    private val _collectorDetail = MutableLiveData<List<CollectorDetailModel>>()
    val collectorDetail: LiveData<List<CollectorDetailModel>>
        get() = _collectorDetail

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
        collectorsRepository.refreshData({
            _collectors.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            Log.d("Error", it.toString())
            _eventNetworkError.value = true
        })
    }
    //Actualiza los detalles de un coleccionista obteniéndolos desde el repositorio y publica en los LiveData correspondientes.
    fun refreshDataDetailFromNetwork(collectorId: Int) {
        collectorsRepository.refreshDetailData(collectorId, { collectorDetail ->
            _collectorDetail.postValue(collectorDetail)
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
            if (modelClass.isAssignableFrom(CollectorViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}