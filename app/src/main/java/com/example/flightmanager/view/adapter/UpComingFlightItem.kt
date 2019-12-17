package com.example.flightmanager.view.adapter

import com.example.flightmanager.R
import com.example.flightmanager.data.model.BoardingPassModel
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.upcoming_flight_item.view.*

class UpComingFlightItem(val boardingPassModel: BoardingPassModel):Item<GroupieViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.upcoming_flight_item
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.first_name_txt.text=boardingPassModel.firstName
        viewHolder.itemView.last_name.text=boardingPassModel.lastName
        viewHolder.itemView.date_of_board_pass.text=boardingPassModel.date
        viewHolder.itemView.flight_operator_txt.text=boardingPassModel.flightOperator
        viewHolder.itemView.flight_number_txt.text=boardingPassModel.flightNumber
        viewHolder.itemView.booking_ref_tex.text=boardingPassModel.bookingRef
        viewHolder.itemView.to_airport_code_txt.text=boardingPassModel.to
        viewHolder.itemView.from_airport_code_txt.text=boardingPassModel.from
        viewHolder.itemView.to_airport_name_txt.text= boardingPassModel.toAirport
        viewHolder.itemView.from_airport_name_txt.text=boardingPassModel.fromAirport

    }
}