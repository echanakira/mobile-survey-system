package cmsc436.mobilesurvey.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import cmsc436.mobilesurvey.R
import cmsc436.mobilesurvey.forms.SurveyActivity
import cmsc436.mobilesurvey.utils.getFirstWord
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var selectedSurvey: String? = null
    private var viewSurveyButton: Button? = null
    internal var registerBtn: Button? = null
    internal var loginBtn: Button? = null
    private var progressBar: ProgressBar? = null
    private var userEmail: EditText? = null
    private var userPassword: EditText? = null

    val db = FirebaseFirestore.getInstance()
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        initializeSpinner()

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
            // attempt to get their document. If doc exists, proceed. if not, show error and don't sign in
            val docRef = db.collection("users").document(userId)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                        intent.putExtra("userId", userId)
                        startActivity(intent)
                    } else {
                        mAuth!!.signOut()
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
        }
    }

    fun initializeSpinner() {
        val spinner = findViewById<Spinner>(R.id.test_spinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.survey_type, android.R.layout.simple_spinner_dropdown_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.onItemSelectedListener = this
        spinner.adapter = adapter

        viewSurveyButton = findViewById(R.id.view_survey)
        viewSurveyButton!!.setOnClickListener {
            openSurvey()
        }
    }

    private fun openSurvey() {
        var intent: Intent?

        val restaurant: String = getString(R.string.restaurant)
        val retail: String = getString(R.string.retail)
        val amusement: String = getString(R.string.amusement)

        intent = Intent(this, SurveyActivity::class.java)

        when (selectedSurvey) {
            restaurant -> {
                intent.putExtra("type", getFirstWord(restaurant))
            }

            retail -> {
                intent.putExtra("type", getFirstWord(retail))

            }

            amusement -> {
                intent.putExtra("type", getFirstWord(amusement))
            }

            else -> {
                return
            }
        }

        startActivity(intent)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // parent.getItemAtPosition(pos) to get value
        var value = parent.getItemAtPosition(pos)
        selectedSurvey = value.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
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
