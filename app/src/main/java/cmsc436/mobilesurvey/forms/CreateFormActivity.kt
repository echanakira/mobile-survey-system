package cmsc436.mobilesurvey.forms

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import cmsc436.mobilesurvey.R


class CreateFormActivity : AppCompatActivity(){
    private var surveyName : EditText? = null
    //TODO(Elijah): Represent Survey Type
    private var mAuth : FirebaseAuth? = null


    //TODO(Elijah): Add logout button?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_form)
        mAuth = FirebaseAuth.getInstance()
    }
}


