package com.example.flightmanager.view.broadPass


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.flightmanager.R
import com.example.flightmanager.contract.BottomSheetContract
import com.example.flightmanager.presenter.boarpasspresenter.BoardingPassPresenter
import com.example.flightmanager.presenter.boarpasspresenter.BottomSheetPresenter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet.*
import java.time.Instant
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult


/**
 * A simple [Fragment] subclass.
 */
 class BottomSheetFragment : BottomSheetDialogFragment(),BottomSheetContract.View {


    companion object{
    private val PICK_PDF_CODE=100
}

    val boardingPassPresenter = BottomSheetPresenter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet,container,false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        select_board_pass_doc.setOnClickListener {
            boardingPassPresenter.selectBoardingPassDocumentClick()
            dialog?.cancel()
        }
        scan_qr_code.setOnClickListener {
            boardingPassPresenter.scanQrCodeClick()
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
        startActivityForResult(Intent.createChooser(pdfDocumentIntent,"Select pdf"), PICK_PDF_CODE)


    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== PICK_PDF_CODE&&resultCode==Activity.RESULT_OK&&data!=null){
            Toast.makeText(context,"pdf selected",Toast.LENGTH_SHORT).show()
        }
        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null){

            if(result.contents != null){
               Toast.makeText(context,result.contents.toString(),Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context,"null",Toast.LENGTH_SHORT).show()

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
    override fun scanQrCode() {
        val scanner= IntentIntegrator.forSupportFragment(this)
        scanner.setBarcodeImageEnabled(true)
        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        scanner.setBeepEnabled(false)
        scanner.setOrientationLocked(false)
        scanner.initiateScan()
    }

}




