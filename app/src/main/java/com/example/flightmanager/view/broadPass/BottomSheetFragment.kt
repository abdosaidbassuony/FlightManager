package com.example.flightmanager.view.broadPass


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flightmanager.R
import com.example.flightmanager.contract.BottomSheetContract
import com.example.flightmanager.view.AbstractPresenter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet.*
import com.google.zxing.integration.android.IntentIntegrator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


/**
 * A simple [Fragment] subclass.
 */
 class BottomSheetFragment : BottomSheetDialogFragment(),BottomSheetContract.View {


    companion object{
     val PICK_PDF_CODE=100
}

   override val presenter : BottomSheetPresenter by inject { parametersOf(this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet,container,false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        select_board_pass_doc.setOnClickListener {
            presenter.selectBoardingPassDocumentClick()
            dialog?.cancel()
        }
        scan_qr_code.setOnClickListener {
            presenter.scanQrCodeClick()
            dialog?.cancel()
        }
        scan_downloads.setOnClickListener {
            dialog?.cancel()
        }
    }
    override fun selectBoardingPassDocument() {
        val pdfDocumentIntent=Intent(Intent.ACTION_GET_CONTENT)
        pdfDocumentIntent.type="application/pdf"
        pdfDocumentIntent.addCategory(Intent.CATEGORY_OPENABLE)
       activity?.startActivityForResult(Intent.createChooser(pdfDocumentIntent,"Select pdf"), PICK_PDF_CODE)


    }

    override fun scanQrCode() {
        val scanner= IntentIntegrator.forSupportFragment(this)
        scanner.setBarcodeImageEnabled(true)
        scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        scanner.setBeepEnabled(false)
        scanner.setOrientationLocked(false)
        scanner.initiateScan()
    }


}




