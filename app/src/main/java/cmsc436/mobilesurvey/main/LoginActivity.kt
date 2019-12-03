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

class LoginActivity : AppCompatActivity() {
    private var userEmail: EditText? = null
    private var userPassword: EditText? = null
    private var loginBtn: Button? = null
    private var progressBar: ProgressBar? = null


    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        mAuth = FirebaseAuth.getInstance()

        initializeUI()

        loginBtn!!.setOnClickListener { loginUserAccount() }
    }

    fun login() {
        Log.i(TAG, "Login clicked")
    }

    //TODO(Elijah): Position progress bar
    public fun loginUserAccount() {
        progressBar!!.visibility = View.VISIBLE

        val email: String = userEmail?.text.toString().toLowerCase()
        val password: String = userPassword?.text.toString()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter email...", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Please enter password!", Toast.LENGTH_LONG).show()
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
                    val intent = Intent(this@LoginActivity, DashboardActivity::class.java)

                    intent.putExtra("userId", userId)
                    intent.putExtra("user", email.split("@")[0])

                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Login failed! Please try again later",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

    }


    private fun initializeUI() {
        userEmail = findViewById(R.id.email)
        userPassword = findViewById(R.id.password)

        loginBtn = findViewById(R.id.login)
        progressBar = findViewById(R.id.progressBar)
    }

    companion object {
        val UserMail = "com.example.tesla.myhomelibrary.UMail"
        val UserID = "com.example.tesla.myhomelibrary.UID"
        val TAG = "LoginActivity"
    }
}
