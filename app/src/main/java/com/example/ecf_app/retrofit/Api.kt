package com.example.ecf_app.retrofit


import com.example.ecf_app.json.Medecin
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("connexion.php?matricule")
    suspend fun getMedecin(@Query("matricule") matricule : Int): Response<Medecin>

}