package com.example.ecf_app.json

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListPrescriptionItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("liste_medicament")
    val liste_medicament: String,
    @SerializedName("patient")
    val patient: Int
): Serializable {
    override fun toString(): String {
        return "($id) $liste_medicament $patient \n"
    }
}