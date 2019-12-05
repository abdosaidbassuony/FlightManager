package com.example.flightmanager.view.broadPass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.flightmanager.R
import com.example.flightmanager.contract.BoardingPassContract
import com.example.flightmanager.contract.BottomSheetContract
import com.example.flightmanager.data.network.AirPortApiService
import com.example.flightmanager.presenter.boarpasspresenter.BoardingPassPresenter
import com.example.flightmanager.view.setting.SettingActivity
import kotlinx.android.synthetic.main.activity_board_pass.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BoardPassActivity : AppCompatActivity() ,BoardingPassContract.View{

    val boardingPassPresenter =BoardingPassPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_pass)
        setSupportActionBar(board_pass_toolbar)

        add_board_pass_btn.setOnClickListener {

            boardingPassPresenter.addBoardingPassClick()

        }

    }
    override fun showBottomSheet() {
        val bottomSheetFragment = BottomSheetFragment()
        bottomSheetFragment.show(supportFragmentManager,"bottom sheet")
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.board_pass_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting -> {
                Log.d("setting","open")
               val intent =Intent(this,SettingActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


