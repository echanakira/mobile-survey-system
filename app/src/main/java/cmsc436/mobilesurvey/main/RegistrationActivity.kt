package cmsc436.mobilesurvey.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import cmsc436.mobilesurvey.R

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Collections.replaceAll
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import cmsc436.mobilesurvey.utils.getFirstWord

class RegistrationActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var selectedPlaceType: String? = null
    private var nameTV: EditText? = null
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

        val spinner = findViewById<Spinner>(R.id.test_spinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.survey_type, android.R.layout.simple_spinner_dropdown_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.onItemSelectedListener = this
        spinner.adapter = adapter
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // parent.getItemAtPosition(pos) to get value
        var value = parent.getItemAtPosition(pos)
        selectedPlaceType = value.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    private fun registerNewUser() {
        progressBar!!.visibility = View.VISIBLE

        val name: String = nameTV!!.text.toString()
        val email: String = emailTV!!.text.toString()
        val password: String = passwordTV!!.text.toString()

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(applicationContext, "Please enter name", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter email", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Please enter password", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedPlaceType == null) {
            Toast.makeText(applicationContext, "Please select place type", Toast.LENGTH_SHORT)
                .show()
            return
        }

        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val type: String =
                        selectedPlaceType.toString()
                    val parsed = getFirstWord(type)
                    val userId = mAuth?.currentUser?.uid!!

                    val data = hashMapOf(
                        "name" to name,
                        "email" to email,
                        "type" to parsed
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
                                        Intent(
                                            this@RegistrationActivity,
                                            DashboardActivity::class.java
                                        )

                                    intent.putExtra("userId", userId)
                                    intent.putExtra("user", email.split("@")[0])

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
        nameTV = findViewById(R.id.name)
        emailTV = findViewById(R.id.email)
        passwordTV = findViewById(R.id.password)
        regBtn = findViewById(R.id.register)
        progressBar = findViewById(R.id.progressBar)
    }
}
