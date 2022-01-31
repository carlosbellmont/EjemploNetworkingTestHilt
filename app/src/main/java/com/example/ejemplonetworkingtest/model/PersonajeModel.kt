package com.example.ejemplonetworkingtest.model

import java.io.Serializable


data class PersonajeModel(val id: Int, val personaje: String, val imagen: String) : Serializable {
    fun getNombreCorto() = run { personaje.substringBefore(" ") }
}