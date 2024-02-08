package com.example.ecf_app.json

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListPatientItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nom")
    val nom: String,
    @SerializedName("prenom")
    val prenom: String
): Serializable {
    override fun toString(): String {
        return "($id) $nom $prenom \n"
    }
}