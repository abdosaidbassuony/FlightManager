package com.example.flightmanager.data.model

data class BoardingPassModel(
    val firstName:String,
    val lastName:String,
    val bookingRef:String,
    val from:String,
    val to:String,
    val flightOperator:String,
    val flightNumber:String,
    val date:String,
    val flightClass:String,
    val seat:String
)