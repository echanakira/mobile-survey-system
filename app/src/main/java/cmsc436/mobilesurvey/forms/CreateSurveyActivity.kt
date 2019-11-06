package cmsc436.mobilesurvey.forms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
<<<<<<< HEAD:app/src/main/java/cmsc436/mobilesurvey/CreateSurveyActivity.kt
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
=======
import cmsc436.mobilesurvey.R
>>>>>>> f13195bde3b2f4697c4c3908bbc398ad06e7a659:app/src/main/java/cmsc436/mobilesurvey/forms/CreateSurveyActivity.kt


class CreateSurveyActivity : AppCompatActivity() {

    private var createButton: Button? = null
    private var name: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createsurvey)

        name = findViewById(R.id.name)
        createButton = findViewById(R.id.create)
        val spinner = findViewById<Spinner>(R.id.spinner)

        val adapter = ArrayAdapter.createFromResource(this,
            R.array.survey_type,android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        //TODO Actually create the surveys
        //TODO Link survey to current login
    }
}