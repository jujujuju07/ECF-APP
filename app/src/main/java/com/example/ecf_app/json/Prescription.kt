package com.example.ecf_app.json

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Prescription(
    @SerializedName("id")
    val id: Int,
    @SerializedName("liste_medicament")
    val liste_medicament: String,
    @SerializedName("posologie")
    val posologie: String,
    @SerializedName("date_debut")
    val date_debut: String,
    @SerializedName("date_fin")
    val date_fin: String,
    @SerializedName("patient")
    val patient: Int
): Serializable {
    override fun toString(): String {
        return "($id) $liste_medicament $posologie $date_debut $date_fin $patient\n"
    }
}