package com.example.flightmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "board_pass")
data class BoardingPassModel(
    var firstName:String,
    var lastName:String,
    var bookingRef:String,
    var from:String,
    var to:String,
    var flightOperator:String,
    var flightNumber:String,
    var date:String,
    var flightClass:String,
    var seat:String,
    var fromAirport: String,
    var toAirport:String
){
    @PrimaryKey(autoGenerate = true)
    var  id: Int = 0
}