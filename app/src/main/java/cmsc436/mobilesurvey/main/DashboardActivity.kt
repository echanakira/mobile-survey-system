package cmsc436.mobilesurvey.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import cmsc436.mobilesurvey.R
import cmsc436.mobilesurvey.forms.AmusementActivity
import cmsc436.mobilesurvey.forms.CreateSurveyActivity
import cmsc436.mobilesurvey.forms.RestaurantActivity
import cmsc436.mobilesurvey.forms.RetailActivity
import cmsc436.mobilesurvey.utils.*
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var selectedSurvey: String? = null
    private var viewSurveyButton: Button? = null
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

        userId = mAuth!!.currentUser!!.uid
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // parent.getItemAtPosition(pos) to get value
        var value = parent.getItemAtPosition(pos)
        selectedSurvey = value.toString()

        Toast.makeText(applicationContext, "$value", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    override fun onStart() {
        super.onStart()
    }

    private fun openSurvey() {
        var intent: Intent?

        val restaurant: String = getString(R.string.restaurant)
        val retail: String = getString(R.string.retail)
        val amusement: String = getString(R.string.amusement)

        when (selectedSurvey) {
            restaurant -> {
                intent = Intent(this, RestaurantActivity::class.java)
            }

            retail -> {
                intent = Intent(this, RetailActivity::class.java)
            }

            amusement -> {
                intent = Intent(this, AmusementActivity::class.java)
            }

            else -> {
                return
            }
        }

        startActivity(intent)
    }


    private fun logout() {
        mAuth!!.signOut()
        finish()
    }
}