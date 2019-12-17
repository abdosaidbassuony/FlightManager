package com.example.flightmanager.contract

import androidx.lifecycle.LifecycleOwner
import com.example.flightmanager.data.model.BoardingPassModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.sampleapp.util.mvp.BasePresenter
import org.koin.sampleapp.util.mvp.BaseView

interface BoardingPassContract {
    interface View{
        fun showBottomSheet()
        fun showListOfBoardingPass()
        fun onSuccess()
        fun onFaile()
        fun airportName()


    }
    interface Presenter{
        fun addBoardingPassClick()
        suspend fun  getAirportName(code:String):String
        fun getAllBoardPass(lifecycleOwner: LifecycleOwner,adapter: GroupAdapter<GroupieViewHolder>,previousAdapter: GroupAdapter<GroupieViewHolder>)
        fun insertNewBoardingpass(boardingPassModel: BoardingPassModel)
        fun getPreviousFlight(lifecycleOwner: LifecycleOwner,adapter: GroupAdapter<GroupieViewHolder>)
        fun deleteAll()



    }
}