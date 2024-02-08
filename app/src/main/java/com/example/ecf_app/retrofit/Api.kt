package com.example.ecf_app.retrofit


import com.example.ecf_app.json.Avis
import com.example.ecf_app.json.ListAvis
import com.example.ecf_app.json.ListPatient
import com.example.ecf_app.json.ListPrescription
import com.example.ecf_app.json.Medecin
import com.example.ecf_app.json.Prescription
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @GET("connexion.php?matricule")
    suspend fun getMedecin(@Query("matricule") matricule : Int): Response<Medecin>

    @GET("listPatient.php?idMedecin")
    suspend fun getListPatient(@Query("idMedecin") matricule: Int): Response<ListPatient>

    @GET("listAvis.php?idPatient")
    suspend fun getListAvis(@Query("idPatient") patient: Int): Response<ListAvis>

    @GET("listPrescription.php?idPatient")
    suspend fun getListPrescription(@Query("idPatient") patient: Int): Response<ListPrescription>

    @GET("consultationPrescription.php?idPrescription")
    suspend fun getPrescription(@Query("idPrescription") prescription: Int): Response<Prescription>

    @GET("consultationAvis.php?idAvis")
    suspend fun getAvis(@Query("idAvis")avis:Int): Response<Avis>

    @GET("modifierPrescription.php?id&liste_medicament&posologie&date_debut&date_fin&patient")
    suspend fun getModifierPrescription(@Query("id")id:Int,@Query("liste_medicament")liste_medicament:String,@Query("posologie")posologie:String,@Query("date_debut")date_debut:String,@Query("date_fin")date_fin:String,@Query("patient")patient:Int)

    @GET("ajouterPrescription.php?liste_medicament&posologie&date_debut&date_fin&patient")
    suspend fun setPrescription(@Query("liste_medicament")liste_medicament: String,@Query("posologie")posologie: String,@Query("date_debut")date_debut:String,@Query("date_fin")date_fin:String,@Query("patient")patient:Int)

    @GET("ajouterAvis.php?TitreAvis&Date&Description&NomPrenomMedecin&patient")
    suspend fun setAvis(@Query("TitreAvis")TitreAvis:String,@Query("Date")Date:String,@Query("Description")Description:String,@Query("NomPrenomMedecin")NomPrenomMedecin:String,@Query("patient")patient:Int)
}