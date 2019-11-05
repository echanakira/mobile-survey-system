package cmsc436.mobilesurvey.forms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import cmsc436.mobilesurvey.R


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

        //TODO Add this class to the intent filter
        //TODO Actually create the surveys
    }
}