package com.example.ecf_app.json

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Medecin(
    @SerializedName("id")
    val id: Int,
    @SerializedName("matricule")
    val matricule: Int,
    @SerializedName("nom")
    val nom: String,
    @SerializedName("prenom")
    val prenom: String,
    @SerializedName("specialite")
    val specialite: String
): Serializable {
    override fun toString(): String {
        return "($id) $matricule $nom $prenom $specialite \n"
    }
}