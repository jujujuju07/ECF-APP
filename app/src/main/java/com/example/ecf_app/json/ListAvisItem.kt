package com.example.ecf_app.json

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListAvisItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("patient")
    val patient: Int,
    @SerializedName("titre")
    val titre: String
): Serializable {
    override fun toString(): String {
        return "($id) $patient $titre \n"
    }
}