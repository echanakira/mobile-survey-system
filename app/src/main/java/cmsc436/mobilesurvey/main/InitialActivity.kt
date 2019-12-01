package cmsc436.mobilesurvey.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import android.content.Intent
import cmsc436.mobilesurvey.R
import cmsc436.mobilesurvey.utils.ScanActivity

class InitialActivity : AppCompatActivity() {

    private var loginButton: Button? = null
    private var scanButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        loginButton = findViewById(R.id.initial_login)
        scanButton = findViewById(R.id.initial_scan)

        loginButton!!.setOnClickListener {

            val intent = Intent(this@InitialActivity,MainActivity::class.java)
            startActivity(intent)
        }

        scanButton!!.setOnClickListener {
            val intent = Intent(this@InitialActivity,ScanActivity::class.java)
            startActivity(intent)
        }
    }
}