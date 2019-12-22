package com.example.flightmanager.view.broadPass

import com.example.flightmanager.contract.BottomSheetContract
import com.example.flightmanager.view.AbstractPresenter

class BottomSheetPresenter(override var view: BottomSheetContract.View): AbstractPresenter<BottomSheetContract.View, BottomSheetContract.Presenter>(),BottomSheetContract.Presenter {

    override fun scanQrCodeClick() {
        view.scanQrCode()
    }

    override fun stop() {

    }

    override fun selectBoardingPassDocumentClick() {
        view.selectBoardingPassDocument()
    }



}