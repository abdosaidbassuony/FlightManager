package com.example.flightmanager.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flightmanager.R
import com.example.flightmanager.view.broadPass.BoardPassActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val backGround =object :Thread(){
            override fun run() {
                try {
                    sleep(5000)
                    val intent =Intent(baseContext,BoardPassActivity::class.java)
                    startActivity(intent)

                }catch (e:Exception){
                       e.printStackTrace()
                }

            }
        }
        backGround.start()
    }
}
