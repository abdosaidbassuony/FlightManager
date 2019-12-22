package com.example.flightmanager.data.model

import android.os.Parcelable
import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "board_pass")
@Parcelize
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
    var toAirport:String,
    var qrScaneImageView:String
):Parcelable{
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var  id: Int = 0
}