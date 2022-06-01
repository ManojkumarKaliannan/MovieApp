package com.backbase.assignment.ui.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.backbase.assignment.R
import com.backbase.assignment.ui.ui.dashboard.DashBoardActivity

/*Application should not provide its own launch screen, From API 31 onwards we need to use SplashScreen API
* Refer https://developer.android.com/guide/topics/ui/splash-screen/migrate*/
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            //Preventing splash activity from displaying from API 31 and above
            //Using Splashscreen API
            //setKeepOnScreenCondition - to keep the routing activity in place but stop it from rendering
            splashScreen.setKeepOnScreenCondition{true}
        }
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, DashBoardActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}