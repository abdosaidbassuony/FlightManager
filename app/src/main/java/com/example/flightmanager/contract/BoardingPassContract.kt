package com.example.flightmanager.contract

interface BoardingPassContract {
    interface View{
        fun showBottomSheet()


    }
    interface Presenter{
        fun addBoardingPassClick()

    }
}