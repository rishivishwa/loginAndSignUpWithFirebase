package com.example.matromonialportel

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    private val splashDuration: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            if (isUserLoggedIn()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, splashDuration)
    }
    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLogin", false)
    }
}