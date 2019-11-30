package cmsc436.mobilesurvey.forms

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cmsc436.mobilesurvey.R

class AmusementActivity : AppCompatActivity(){

    private var submitButton : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amusement)

        submitButton = findViewById(R.id.submit)
    }
}