package com.example.flightmanager.view.broadPass

import com.example.flightmanager.contract.BottomSheetContract

class BottomSheetPresenter(val Iview:BottomSheetContract.View):BottomSheetContract.Presenter {

    override fun scanQrCodeClick() {
        Iview.scanQrCode()
    }

    override fun selectBoardingPassDocumentClick() {
        Iview.selectBoardingPassDocument()
    }
}