package cmsc436.mobilesurvey.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import cmsc436.mobilesurvey.R
import cmsc436.mobilesurvey.forms.CreateSurveyActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.integration.android.IntentIntegrator

class DashboardActivity : AppCompatActivity() {
    private var logoutButton: Button? = null
    private var mAuth: FirebaseAuth? = null
    private var createBtn: Button? = null
    private var scanBtn: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        mAuth = FirebaseAuth.getInstance()
        logoutButton = findViewById(R.id.logout)
        logoutButton!!.setOnClickListener { logout() }

        createBtn = findViewById(R.id.form)
        createBtn!!.setOnClickListener{
            val intent = Intent(this@DashboardActivity, CreateSurveyActivity::class.java)
            startActivity(intent)
        }

        scanBtn = findViewById(R.id.scan)
        scanBtn!!.setOnClickListener {
            val intent = Intent(applicationContext, ScanActivity::class.java)
            startActivityForResult(intent, 0)
        }


    }

    private fun logout() {
        mAuth!!.signOut()
        finish()
    }

}