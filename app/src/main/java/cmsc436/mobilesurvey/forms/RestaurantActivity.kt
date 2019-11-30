package cmsc436.mobilesurvey.forms

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import cmsc436.mobilesurvey.R
import kotlinx.android.synthetic.main.activity_restaurant.*
import cmsc436.mobilesurvey.utils.*

class RestaurantActivity : AppCompatActivity() {
    private var surveyName = "restaurant"
    private var submitButton: Button? = null
    private var foodQuality: String? = null // first question
    private var serviceQuality: String? = null // second question
    private var cleanliness: String? = null // third question
    private var atmosphere: String? = null // fourth question
    private var recommend: String? = null // fifth question
    private var noRecommendationComment: EditText? = null
    private var additionalComments: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        // food quality
        one_group.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            var value = radio.text
            foodQuality = value as String?

            Toast.makeText(
                applicationContext, "Food quality: $value",
                Toast.LENGTH_SHORT
            ).show()
        }

        // quality of service
        two_group.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            var value = radio.text
            serviceQuality = value as String?

            Toast.makeText(
                applicationContext, "Service quality: $value",
                Toast.LENGTH_SHORT
            ).show()
        }

        // cleanliness
        three_group.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            var value = radio.text
            cleanliness = value as String?

            Toast.makeText(
                applicationContext, "Cleanliness: $value",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Atmosphere
        four_group.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            var value = radio.text
            atmosphere = value as String?

            Toast.makeText(
                applicationContext, "Atmosphere: $value",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Recommend to friends and family?
        five_group.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            var value = radio.text
            recommend = value as String?

            Toast.makeText(
                applicationContext, "Recommend to friends/family?: $value",
                Toast.LENGTH_SHORT
            ).show()
        }

        noRecommendationComment = findViewById(R.id.six_text)
        additionalComments = findViewById(R.id.seven_comments)

        submitButton = findViewById(R.id.submit)

        submitButton!!.setOnClickListener {
            submitData()
        }

    }

    private fun submitData() {
        if (foodQuality == null || serviceQuality == null || cleanliness == null || atmosphere == null || recommend == null) {

            Toast.makeText(
                this, "Please fill out all questions",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val noRecComment: String = noRecommendationComment!!.text.toString()
        val addComment: String = additionalComments!!.text.toString()

        Database.submitForm(applicationContext, surveyName, HashMap())

        // Update one field, creating the document if it does not already exist.
//        val data = hashMapOf("capital" to true)
//
//        db.collection("cities").document("BJ")
//            .set(data, SetOptions.merge())
    }
}