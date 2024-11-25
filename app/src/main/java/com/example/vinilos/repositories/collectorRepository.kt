package com.example.vinilos.repositories
import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.model.CollectorDetailModel
import com.example.vinilos.model.CollectorModel
import com.example.vinilos.network.NetworkServiceAdapter
import java.util.stream.Collector

class collectorRepository (val application: Application) {

        fun refreshData(callback: (List<CollectorModel>)->Unit, onError: (VolleyError)->Unit){
            //Determinar la fuente de datos que se va a utilizar.
            NetworkServiceAdapter.getInstance(application).getCollector({
                //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
                callback(it)
            },
                onError
            )
        }
        fun refreshDetailData(
            collectorId: Int,
            callback: (List<CollectorDetailModel>) -> Unit,
            onError: (VolleyError) -> Unit
        ) {
            NetworkServiceAdapter.getInstance(application).getCollectorDetails(
                collectorId, {
                        collectorDetail ->
                    callback(collectorDetail)
                },
                onError
            )
        }
}