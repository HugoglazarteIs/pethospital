package com.example.pethospital.model

import java.io.Serializable

data class Parte (
    var diagnostico: String,
    var causas: String,
    var medicamento: String,
    var tratamiento: String,
    var reposo: String,
    var mascotaID: String
):Serializable