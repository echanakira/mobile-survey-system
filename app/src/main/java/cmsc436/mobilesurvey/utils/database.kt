package cmsc436.mobilesurvey.utils

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

object Database {
    val db = FirebaseFirestore.getInstance()

    fun submitForm(applicationContext: Context, surveyName: String, data: HashMap<Any, Any>){
        Toast.makeText(
            applicationContext, "Submitting $surveyName survey ...",
            Toast.LENGTH_SHORT
        ).show()
    }
}