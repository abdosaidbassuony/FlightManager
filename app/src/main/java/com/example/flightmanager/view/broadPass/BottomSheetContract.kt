package com.example.flightmanager.contract

import org.koin.sampleapp.util.mvp.BasePresenter
import org.koin.sampleapp.util.mvp.BaseView

interface BottomSheetContract {
    interface View:BaseView<Presenter>{
        fun selectBoardingPassDocument()
        fun scanQrCode()

    }
    interface Presenter:BasePresenter<View>{

        fun selectBoardingPassDocumentClick()
        fun scanQrCodeClick()
    }
}