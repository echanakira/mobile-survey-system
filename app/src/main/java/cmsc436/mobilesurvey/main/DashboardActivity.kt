package cmsc436.mobilesurvey.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import cmsc436.mobilesurvey.utils.*
import cmsc436.mobilesurvey.main.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import cmsc436.mobilesurvey.R
import cmsc436.mobilesurvey.forms.CreateSurveyActivity
import cmsc436.mobilesurvey.utils.ScanActivity
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {
    private var logoutButton: Button? = null
    private var viewResponsesButton: Button? = null
    private var createFormButton: Button? = null
    private var mAuth: FirebaseAuth? = null
    private var scanBtn: Button? = null


    internal lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        mAuth = FirebaseAuth.getInstance()
        logoutButton = findViewById(R.id.logout)
        logoutButton!!.setOnClickListener { logout() }

        createFormButton = findViewById(R.id.form)
        createFormButton!!.setOnClickListener {
            val intent = Intent(this, CreateSurveyActivity::class.java)
            intent.putExtra("user",this.intent.getStringExtra("user"))
            startActivity(intent)
        }

        viewResponsesButton = findViewById(R.id.responses)
        viewResponsesButton!!.setOnClickListener {
            val intent = Intent(this, ResponsesActivity::class.java)
            startActivity(intent)
        }

        userId = mAuth!!.currentUser!!.uid

        scanBtn = findViewById(R.id.scan)
        scanBtn!!.setOnClickListener {
            val intent = Intent(this@DashboardActivity, ScanActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
    }


    private fun logout() {
        mAuth!!.signOut()
        finish()
    }

    companion object {
        var tvresult: TextView? = null
    }
}