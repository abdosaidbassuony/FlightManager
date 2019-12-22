package com.example.flightmanager.contract

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.example.flightmanager.data.model.BoardingPassModel
import com.example.flightmanager.view.adapter.UpComingFlightItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import org.koin.sampleapp.util.mvp.BasePresenter
import org.koin.sampleapp.util.mvp.BaseView

interface BoardingPassContract {
    interface View:BaseView<Presenter>{
        fun showBottomSheet()
        fun showListOfBoardingPass()
        fun onSuccess()
        fun onFaile()
        fun airportName()
        fun showBoardPassInf(upComing:Item<GroupieViewHolder>)
        fun showNotification()


    }
    interface Presenter:BasePresenter<View>{
        fun addBoardingPassClick()
        suspend fun  getAirportName(code:String):String
        fun getAllBoardPass(lifecycleOwner: LifecycleOwner,adapter: GroupAdapter<GroupieViewHolder>,previousAdapter: GroupAdapter<GroupieViewHolder>)
        fun insertNewBoardingpass(boardingPassModel: BoardingPassModel)
        fun getPreviousFlight(lifecycleOwner: LifecycleOwner,adapter: GroupAdapter<GroupieViewHolder>)
        fun deleteAll()
        fun navigatToBoardPassInfo(upComing: Item<GroupieViewHolder>)
        fun Notification(lifecycleOwner: LifecycleOwner,context: Context)



    }
}