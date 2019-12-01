package cmsc436.mobilesurvey.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import cmsc436.mobilesurvey.R
import cmsc436.mobilesurvey.forms.CreateSurveyActivity
import cmsc436.mobilesurvey.utils.*
import cmsc436.mobilesurvey.main.*
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {
    private var logoutButton: Button? = null
    private var viewResponsesButton: Button?=null
    private var createFormButton: Button? = null
    private var mAuth: FirebaseAuth? = null

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
            startActivity(intent)
        }

        viewResponsesButton = findViewById(R.id.responses)
        viewResponsesButton!!.setOnClickListener {
            val intent = Intent(this, ResponsesActivity::class.java)
            startActivity(intent)
        }

        userId = mAuth!!.currentUser!!.uid
    }

    override fun onStart() {
        super.onStart()
    }


    private fun logout() {
        mAuth!!.signOut()
        finish()
    }
}