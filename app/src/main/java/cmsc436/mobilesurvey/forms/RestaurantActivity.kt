package cmsc436.mobilesurvey.forms

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import cmsc436.mobilesurvey.R

class RestaurantActivity: AppCompatActivity(){

    private var submitButton : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        submitButton = findViewById(R.id.submit)
    }
}