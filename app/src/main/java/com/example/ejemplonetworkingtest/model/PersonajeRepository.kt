package com.example.ejemplonetworkingtest.model

import com.example.ejemplonetworkingtest.data.OperationCallback

interface PersonajeRepository {
    fun fetchPersonajesCallback(callback: OperationCallback<PersonajeModel>)
    fun cancel()
}