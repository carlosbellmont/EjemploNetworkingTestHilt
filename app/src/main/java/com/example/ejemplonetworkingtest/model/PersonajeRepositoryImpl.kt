package com.example.ejemplonetworkingtest.model

import com.example.ejemplonetworkingtest.data.OperationCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PersonajeRepositoryImpl @Inject constructor(
    private val personajeDataSource: PersonajeDataSource
    ): PersonajeRepository {

    override fun fetchPersonajesCallback(callback: OperationCallback<PersonajeModel>) {
        personajeDataSource.obtenerPersonajesCallback(callback)
    }

    override fun cancel() {
        personajeDataSource.cancel()
    }
}