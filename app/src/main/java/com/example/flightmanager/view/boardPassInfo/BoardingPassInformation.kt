package com.example.flightmanager.view.boardPassInfo

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.flightmanager.R
import com.example.flightmanager.data.model.BoardingPassModel
import com.example.flightmanager.view.adapter.UpComingFlightItem
import com.example.flightmanager.view.broadPass.BoardPassActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_boarding_pass_information.*

class BoardingPassInformation : AppCompatActivity() {

    @SuppressLint("SdCardPath")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding_pass_information)
        val upComingFlightItem= intent.getParcelableExtra<BoardingPassModel>(BoardPassActivity.BOARD_PASS_KEY)
        supportActionBar?.title = "${upComingFlightItem?.from} > ${upComingFlightItem?.to}"
        supportActionBar?.subtitle = "${upComingFlightItem?.firstName} ${upComingFlightItem?.lastName}"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.colorPrimary))
        window.statusBarColor = Color.parseColor("#ffffff")
        seat_number_info.text= upComingFlightItem!!.seat
        board_pass_info_date.text=upComingFlightItem.date
        flight_operator_tinfo_txt.text=upComingFlightItem.flightOperator
        flight_class_info_text.text =upComingFlightItem.flightClass
        from_airport_code_info_txt.text=upComingFlightItem.from
        to_airport_code_info_txt.text=upComingFlightItem.to
        to_airport_name_info_txt.text =upComingFlightItem.toAirport
        from_airport_name_info_txt.text=upComingFlightItem.fromAirport
        Picasso.get().load(upComingFlightItem.qrScaneImageView).into(qr_scaned_img)







    }
}
