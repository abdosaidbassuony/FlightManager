package com.example.flightmanager.view.broadPass

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.flightmanager.R
import com.example.flightmanager.contract.BoardingPassContract
import com.example.flightmanager.data.model.BoardingPassModel
import com.example.flightmanager.view.adapter.UpComingFlightItem
import com.example.flightmanager.view.boardPassInfo.BoardingPassInformation
import com.example.flightmanager.view.setting.SettingActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_board_pass.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*


class BoardPassActivity : AppCompatActivity(), BoardingPassContract.View {
    companion object{
        val BOARD_PASS_KEY= "BOARD_PASS_KEY"
        val CHANNEL_ID = "1"
    }
    var firstName: String? = null
    var lastName: String? = null
    var bookingRef: String? = null
    var from: String? = null
    var to: String? = null
    var flightOperator: String? = null
    var flightNumber: String? = null
    var date: String? = null
    var flightClass: String? = null
    var seat: String? = null
    var boardingIndex: String? = null
    var qrScaneImage:String?=null
    var boardpass: BoardingPassModel? = null
    override val presenter: BoardingPassPresenter by inject{ parametersOf(this,this)}
    val previousadapterView = GroupAdapter<GroupieViewHolder>()
    val upcomingAdapterView = GroupAdapter<GroupieViewHolder>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_pass)
        setSupportActionBar(board_pass_toolbar)
        add_board_pass_btn.setOnClickListener {
            presenter.addBoardingPassClick()
        }
        upcomingAdapterView.setOnItemClickListener { item, view ->
            presenter.navigatToBoardPassInfo(item)
        }
        presenter.Notification(this,this)


    }
    override fun showBottomSheet() {
        val bottomSheetFragment = BottomSheetFragment()
        bottomSheetFragment.show(supportFragmentManager, "bottom sheet")
    }
    override fun showListOfBoardingPass() {
        GlobalScope.launch(Dispatchers.Main) {
            handelView()
            board_pass_recycle_view.adapter = upcomingAdapterView
            previous_flight_recycleview.adapter = previousadapterView
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSuccess() {
        GlobalScope.launch(Dispatchers.Main) {
            upcomingAdapterView.clear()
            previousadapterView.clear()
            presenter.getAllBoardPass(
                this@BoardPassActivity,
                upcomingAdapterView,
                previousadapterView
            )

        }


    }
    override fun onFaile() {
    }
    override fun airportName() {

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.board_pass_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting -> {
                Log.d("setting", "open")
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == BottomSheetFragment.PICK_PDF_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Toast.makeText(this, "selected", Toast.LENGTH_SHORT).show()
        }
        val result: IntentResult = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result.contents == null) {
            Toast.makeText(this, "Result not Found", Toast.LENGTH_SHORT).show()
        } else {
            val content = result.contents.toString()
            val rowData = content.replace("/", " ")
            val pattern = "\\W+".toRegex()
            val words = pattern.split(rowData).filter { it.isNotBlank() }
            firstName = words[1]
            lastName = words[0].replace("M1", "")
            bookingRef = words[2].replace("E", "")
            from = words[3].substring(0, 3)
            to = words[3].substring(3, 6)
            flightOperator = words[3].substring(6, 8)
            flightNumber = words[4]
            date = words[5].substring(0, 3)
            flightClass = words[5].substring(3, 4)
            seat = words[5].substring(4, 8)
            boardingIndex = words[5].substring(8, 12)
            qrScaneImage = result.barcodeImagePath
            Log.d("barcodeImagePath",qrScaneImage!!)
            val date: String = setDateFormat(date!!)
            GlobalScope.launch(Dispatchers.IO) {
                val fromCode:String? = presenter?.getAirportName(from!!)
                val toCode:String? = presenter?.getAirportName(to!!)

                boardpass = BoardingPassModel(
                    firstName!!,
                    lastName!!,
                    bookingRef!!,
                    from!!,
                    to!!,
                    flightOperator!!,
                    flightNumber!!,
                    date,
                    flightClass!!,
                    seat!!,
                    fromCode!!,
                    toCode!!,
                    qrScaneImage!!
                )
                GlobalScope.launch(Dispatchers.IO) {
                    presenter?.insertNewBoardingpass(boardpass!!)
                }

            }


        }


    }

    override fun onStart() {
        super.onStart()
        // presenter?.deleteAll()
        presenter?.getAllBoardPass(this, upcomingAdapterView, previousadapterView)


    }



    override fun showBoardPassInf(upComing: Item<GroupieViewHolder>) {
        upComing as UpComingFlightItem
        val intent =Intent(this,BoardingPassInformation::class.java)
        intent.putExtra(BOARD_PASS_KEY,upComing.boardingPassModel)
        startActivity(intent)

    }

    fun handelView() {
        board_pass_home_img.visibility = View.GONE
        board_pass_home_txt.visibility = View.GONE
    }
    @SuppressLint("SimpleDateFormat")
    fun setDateFormat(dayOfYear: String): String {
        val c: Calendar = Calendar.getInstance()
        c.set(Calendar.DAY_OF_YEAR, dayOfYear.toInt())
        val da: Date = c.time
        val simpleDateFormat = SimpleDateFormat(" MMM d")
        return simpleDateFormat.format(da)
    }


    override fun showNotification() {

    }

}




