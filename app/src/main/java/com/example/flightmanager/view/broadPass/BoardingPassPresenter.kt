package com.example.flightmanager.view.broadPass

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.renderscript.Allocation
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.flightmanager.contract.BoardingPassContract
import com.example.flightmanager.data.database.BoardingPassDatabse
import com.example.flightmanager.data.model.AirPortModel
import com.example.flightmanager.data.model.BoardingPassModel
import com.example.flightmanager.data.network.AirPortApiService
import com.example.flightmanager.view.adapter.PreviousFlightItem
import com.example.flightmanager.view.adapter.UpComingFlightItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.function.LongFunction

class BoardingPassPresenter(context: Context,  var view: BoardingPassContract.View) :BoardingPassContract.Presenter {
    val databse = BoardingPassDatabse(context)
    val airPortModel: AirPortModel? = null
    override fun addBoardingPassClick() {
        view.showBottomSheet()
    }
    override suspend fun getAirportName(code: String): String {
        val airPortApiService = AirPortApiService.create().getAirPort(code).await()
        view.airportName()
        return airPortApiService[0].name


    }
    override fun getAllBoardPass(lifecycleOwner: LifecycleOwner, adapter: GroupAdapter<GroupieViewHolder>,previousAdapter: GroupAdapter<GroupieViewHolder>) {
            databse.boardingPassDao().getAllBoardPass().observe(lifecycleOwner, androidx.lifecycle.Observer {
               GlobalScope.launch(Dispatchers.Main) {
                   if (it!=null){
                       adapter.clear()
                       previousAdapter.clear()
                      it.forEach {
                          rearrangeRecycleView(adapter,previousAdapter,it)
                      }
                   }
                      view.showListOfBoardingPass()
               }
            })






    }
    @SuppressLint("SimpleDateFormat")
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun rearrangeRecycleView(upComingAdapter: GroupAdapter<GroupieViewHolder>, previousAdapter: GroupAdapter<GroupieViewHolder>, model: BoardingPassModel){
        val dateNow =Date()
        val simpleDateFormat = SimpleDateFormat(" MMM d")
        val date:Date = simpleDateFormat.parse(model.date)
        val newDate =simpleDateFormat.format(dateNow.time)
        val nowDate:Date = simpleDateFormat.parse(newDate)

        if(date.after(nowDate)){
            upComingAdapter.add(UpComingFlightItem(model))
        }else{
            previousAdapter.add(PreviousFlightItem(model))
        }
    }
    override fun insertNewBoardingpass(boardingPassModel: BoardingPassModel) {
        GlobalScope.launch(Dispatchers.IO) {
            databse.boardingPassDao().insertBoardPass(boardingPassModel)
        }
        view.onSuccess()
    }
    override fun getPreviousFlight(lifecycleOwner: LifecycleOwner,adapter: GroupAdapter<GroupieViewHolder>) {
    }
    override fun deleteAll() {
        GlobalScope.launch(Dispatchers.IO) {
            databse.boardingPassDao().deleteAll()
        }

    }
}