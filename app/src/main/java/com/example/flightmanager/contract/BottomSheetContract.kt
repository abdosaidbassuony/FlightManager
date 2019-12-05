package com.example.flightmanager.contract

interface BottomSheetContract {
    interface View{
        fun selectBoardingPassDocument()
        fun scanQrCode()

    }
    interface Presenter{

        fun selectBoardingPassDocumentClick()
        fun scanQrCodeClick()
    }
}