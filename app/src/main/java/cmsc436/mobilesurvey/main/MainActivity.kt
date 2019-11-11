package cmsc436.mobilesurvey.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import cmsc436.mobilesurvey.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    internal var registerBtn: Button? = null
    internal var loginBtn: Button? = null
    private var progressBar: ProgressBar? = null
    private var mAuth: FirebaseAuth? = null
    private var userEmail: EditText? = null
    private var userPassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()

        registerBtn!!.setOnClickListener {
            Log.i(TAG, "DEBUG INFO: Clicked Register")
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }
        loginBtn!!.setOnClickListener {
            loginUserAccount()
        }

        mAuth = FirebaseAuth.getInstance()

        var userId = mAuth!!.currentUser?.uid

        // if user is signed in, don't show login page
        if (userId != null) {
            val intent = Intent(this@MainActivity, DashboardActivity::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
    }

    fun loginUserAccount() {

        progressBar!!.visibility = View.VISIBLE

        val email: String = userEmail?.text.toString()
        val password: String = userPassword?.text.toString()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter email...", Toast.LENGTH_LONG).show()
            progressBar!!.visibility = View.INVISIBLE

            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Please enter password!", Toast.LENGTH_LONG).show()
            progressBar!!.visibility = View.INVISIBLE
            return
        }


        // Retrieve UID for Current User if Login successful and store in intent, for the key UserID
        // Start Intent DashboardActivity if Registration Successful
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                progressBar!!.visibility = View.GONE
                if (task.isSuccessful) {
                    val userId = mAuth?.currentUser?.uid

                    Toast.makeText(applicationContext, "Login successful!", Toast.LENGTH_LONG)
                        .show()
                    val intent = Intent(this@MainActivity, DashboardActivity::class.java)

                    intent.putExtra("userId", userId)

                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Login failed! Please try again later",
                        Toast.LENGTH_LONG
                    ).show()
                    progressBar!!.visibility = View.INVISIBLE
                }
            }


    }

    private fun initializeViews() {
        registerBtn = findViewById(R.id.register)
        loginBtn = findViewById(R.id.login)
        userEmail = findViewById(R.id.email)
        userPassword = findViewById(R.id.password)
        progressBar = findViewById(R.id.progressBar)
    }

    companion object {
        val TAG = "MainActivity"
    }
}
