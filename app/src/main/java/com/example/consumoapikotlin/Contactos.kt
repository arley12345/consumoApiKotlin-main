package com.example.consumoapikotlin

import com.google.gson.annotations.SerializedName
import java.io.Serializable



data class Contactos(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
): Serializable

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
): Serializable

data class Geo(
    val lat: String,
    val lng: String
): Serializable

data class Company(
    val name: String,
    val bs: String
): Serializable