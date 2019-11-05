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
import android.widget.*
import com.google.firebase.auth.FirebaseAuth


class CreateSurveyActivity : AppCompatActivity() {

    private var createButton: Button? = null
    private var name: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createsurvey)

        name = findViewById(R.id.name)
        createButton = findViewById(R.id.create)
        val spinner = findViewById<Spinner>(R.id.spinner)

        val adapter = ArrayAdapter.createFromResource(this,R.array.survey_type,android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        //TODO Add this class to the intent filter
        //TODO Actually create the surveys
    }
}