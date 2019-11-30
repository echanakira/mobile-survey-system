package cmsc436.mobilesurvey.forms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cmsc436.mobilesurvey.R
import android.widget.*

class RetailActivity : AppCompatActivity(){

    private var submitButton : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retail)

        submitButton = findViewById(R.id.submit)
    }
}