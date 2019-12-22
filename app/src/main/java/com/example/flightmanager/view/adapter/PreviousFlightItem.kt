package com.example.flightmanager.view.adapter

import com.example.flightmanager.R
import com.example.flightmanager.data.model.BoardingPassModel
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.previous_flight_item.view.*
import kotlinx.android.synthetic.main.upcoming_flight_item.view.*

class PreviousFlightItem(val boardingPassModel: BoardingPassModel) : Item<GroupieViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.previous_flight_item
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.previous_flight_first_name_txt.text=boardingPassModel.firstName
        viewHolder.itemView.previous_flight_last_name.text=boardingPassModel.lastName
        viewHolder.itemView.previous_flight_date_of_board_pass.text=boardingPassModel.date
        viewHolder.itemView.previous_flight_flight_operator_txt.text=boardingPassModel.flightOperator
        viewHolder.itemView.previous_flight_flight_number_txt.text=boardingPassModel.flightNumber
        viewHolder.itemView.previous_flight_to_airport_code_txt.text=boardingPassModel.to
        viewHolder.itemView.previous_flight_from_airport_code_txt.text=boardingPassModel.from
        viewHolder.itemView.previous_flight_to_airport_name_txt.text= boardingPassModel.toAirport
        viewHolder.itemView.previous_flight_from_airport_name_txt.text=boardingPassModel.fromAirport

        

    }

}