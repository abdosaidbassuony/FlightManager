package com.example.flightmanager.di



import com.example.flightmanager.contract.BoardingPassContract
import com.example.flightmanager.di.Params.BOARDINGPASS_VIEW
import com.example.flightmanager.view.broadPass.BoardPassActivity
import com.example.flightmanager.view.broadPass.BoardingPassPresenter
import org.koin.core.parameter.DefinitionParameters
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.ScopeID
import org.koin.dsl.module

val module = module {


   factory { BoardingPassPresenter(get(),get { parametersOf(BOARDINGPASS_VIEW) })as BoardingPassContract.Presenter }





}



object Params{
   const val BOARDINGPASS_VIEW = "BOARDINGPASS_VIEW"
}


