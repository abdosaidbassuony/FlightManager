package com.example.flightmanager.presenter.boarpasspresenter

import com.example.flightmanager.contract.BoardingPassContract
import com.example.flightmanager.contract.BottomSheetContract

class BoardingPassPresenter(val iview: BoardingPassContract.View) :BoardingPassContract.Presenter{
    override fun addBoardingPassClick() {
        iview.showBottomSheet()
    }


}