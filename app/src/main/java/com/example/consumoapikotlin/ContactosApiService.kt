package com.example.consumoapikotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.ArrayList

interface ContactosApiService {

    @GET("users/")
    fun obtenerListaContacto(): Call<ArrayList<Contactos>>
}