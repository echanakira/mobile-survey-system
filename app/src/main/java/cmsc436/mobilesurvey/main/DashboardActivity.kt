package cmsc436.mobilesurvey.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import cmsc436.mobilesurvey.R
import cmsc436.mobilesurvey.forms.CreateSurveyActivity
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {
    private var logoutButton: Button? = null
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