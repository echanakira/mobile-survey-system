package cmsc436.mobilesurvey

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


import java.util.ArrayList

class DashboardActivity : AppCompatActivity() {
    private var logoutButton: Button? = null
    private var mAuth: FirebaseAuth? = null

    internal lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        mAuth = FirebaseAuth.getInstance()
        logoutButton = findViewById(R.id.logout)
        logoutButton!!.setOnClickListener { logout() }
        userId = intent.getStringExtra("userId")
    }

    // Todo onStart
    override fun onStart() {
        super.onStart()
    }

    private fun logout() {
        mAuth!!.signOut()
        finish()
    }
}