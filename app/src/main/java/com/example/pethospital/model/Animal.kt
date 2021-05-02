package com.example.pethospital.model

import java.io.Serializable

data class Animal(
    var nombre: String,
    var tipo: String,
    var raza: String,
    var edad: String,
    var causa: String,
    var nombreDoc: String,
    var parte: Parte
):Serializable