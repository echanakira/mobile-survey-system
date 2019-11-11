package cmsc436.mobilesurvey.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import cmsc436.mobilesurvey.R

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistrationActivity : AppCompatActivity() {

    private var emailTV: EditText? = null
    private var passwordTV: EditText? = null
    private var regBtn: Button? = null
    private var progressBar: ProgressBar? = null

    val db = FirebaseFirestore.getInstance()
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        mAuth = FirebaseAuth.getInstance()

        initializeUI()

        regBtn!!.setOnClickListener { registerNewUser() }
    }

    private fun registerNewUser() {
        progressBar!!.visibility = View.VISIBLE

        val email: String
        val password: String
        email = emailTV!!.text.toString()
        password = passwordTV!!.text.toString()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter email...", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Please enter password!", Toast.LENGTH_LONG).show()
            return
        }

        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = mAuth?.currentUser?.uid!!

                    val data = hashMapOf(
                        "name" to "",
                        "email" to email
                    )

                    db.collection("users").document(userId).set(data)
                        .addOnSuccessListener { documentReference ->
                            Toast.makeText(
                                applicationContext,
                                "Registration successful!",
                                Toast.LENGTH_LONG
                            ).show()
                            progressBar!!.visibility = View.GONE

                            mAuth!!.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    progressBar!!.visibility = View.GONE

                                    Toast.makeText(
                                        applicationContext,
                                        "Login successful!",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                    val intent =
                                        Intent(this@RegistrationActivity, DashboardActivity::class.java)

                                    intent.putExtra("userId", userId)

                                    startActivity(intent)
                                }
                    }

                } else {
                    Toast.makeText(
                        applicationContext,
                        "Registration failed! Please try again later",
                        Toast.LENGTH_LONG
                    ).show()
                    progressBar!!.visibility = View.GONE
                }
            }
    }

    private fun initializeUI() {
        emailTV = findViewById(R.id.email)
        passwordTV = findViewById(R.id.password)
        regBtn = findViewById(R.id.register)
        progressBar = findViewById(R.id.progressBar)
    }
}
