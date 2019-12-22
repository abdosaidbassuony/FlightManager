package com.example.flightmanager.view.broadPass

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import com.example.flightmanager.contract.BoardingPassContract
import com.example.flightmanager.data.database.BoardingPassDatabse
import com.example.flightmanager.data.model.AirPortModel
import com.example.flightmanager.data.model.BoardingPassModel
import com.example.flightmanager.data.network.AirPortApiService
import com.example.flightmanager.service.Receiver
import com.example.flightmanager.view.AbstractPresenter
import com.example.flightmanager.view.adapter.PreviousFlightItem
import com.example.flightmanager.view.adapter.UpComingFlightItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


 class BoardingPassPresenter(context: Context, override var view: BoardingPassContract.View) :
    AbstractPresenter<BoardingPassContract.View, BoardingPassContract.Presenter>(), BoardingPassContract.Presenter {
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

    override fun navigatToBoardPassInfo(upComing:Item<GroupieViewHolder>) {
        view.showBoardPassInf(upComing)

    }

    @SuppressLint("SimpleDateFormat")
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun Notification(lifecycleOwner: LifecycleOwner, context: Context) {
        databse.boardingPassDao().getAllBoardPass().observe(lifecycleOwner, androidx.lifecycle.Observer {
            val notifyIntent = Intent(context, Receiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context,0,notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            GlobalScope.launch(Dispatchers.Main) {
                it?.forEach {
                    val simpleDateFormat = SimpleDateFormat(" MMM d")
                    val date = simpleDateFormat.parse(it.date)
                    val calendar:Calendar = Calendar.getInstance().apply {
                        time =date

                    }

                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pendingIntent)
                }

            }
        })



    }

     override fun stop() {
     }
 }