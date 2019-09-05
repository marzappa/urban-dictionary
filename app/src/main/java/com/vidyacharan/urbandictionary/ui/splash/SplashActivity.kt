package com.vidyacharan.urbandictionary.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vidyacharan.urbandictionary.R
import com.vidyacharan.urbandictionary.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

}
