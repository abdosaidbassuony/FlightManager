package com.example.flightmanager.data.model


import com.google.gson.annotations.SerializedName

data class AirPortModel(
    @SerializedName("airportId")
    val airportId: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("cityId")
    val cityId: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("pointsOfSale")
    val pointsOfSale: List<String>,
    @SerializedName("themes")
    val themes: List<Any>
)