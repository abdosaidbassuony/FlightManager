package com.example.flightmanager.di



import android.content.Context
import com.example.flightmanager.contract.BoardingPassContract
import com.example.flightmanager.contract.BottomSheetContract
import com.example.flightmanager.view.broadPass.BoardingPassPresenter
import com.example.flightmanager.view.broadPass.BottomSheetPresenter
import com.example.flightmanager.view.setting.SettingContract
import org.koin.dsl.module

val module = module {


   factory {(context: Context,  view: BoardingPassContract.View)-> BoardingPassPresenter(context,view) }
   factory { (view:BottomSheetContract.View)->BottomSheetPresenter(view) }

}










