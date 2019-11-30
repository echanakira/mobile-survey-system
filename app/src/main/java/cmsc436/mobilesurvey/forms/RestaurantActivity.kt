package cmsc436.mobilesurvey.forms

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import cmsc436.mobilesurvey.R
import kotlinx.android.synthetic.main.activity_restaurant.*

class RestaurantActivity : AppCompatActivity() {

    private var submitButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        one_group.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            Toast.makeText(
                applicationContext, " On checked change : ${radio.text}",
                Toast.LENGTH_SHORT
            ).show()
        }
        
        two_group.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            Toast.makeText(
                applicationContext, " On checked change : ${radio.text}",
                Toast.LENGTH_SHORT
            ).show()
        }

        three_group.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            Toast.makeText(
                applicationContext, " On checked change : ${radio.text}",
                Toast.LENGTH_SHORT
            ).show()
        }

        four_group.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            Toast.makeText(
                applicationContext, " On checked change : ${radio.text}",
                Toast.LENGTH_SHORT
            ).show()
        }

        five_group.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            Toast.makeText(
                applicationContext, " On checked change : ${radio.text}",
                Toast.LENGTH_SHORT
            ).show()
        }

        submitButton = findViewById(R.id.submit)
    }
}