package cmsc436.mobilesurvey.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import cmsc436.mobilesurvey.main.LoginActivity.Companion.TAG
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

object Database {
    val db = FirebaseFirestore.getInstance()

    fun submitSurvey(
        applicationContext: Context,
        surveyType: String?,
        placeName: String?,
        placeId: String?,
        data: HashMap<String, String?>
    ) {

        Toast.makeText(
            applicationContext, "Submitting survey for $placeName",
            Toast.LENGTH_SHORT
        ).show()

        db.collection("responses")
            .add(data)
            .addOnSuccessListener { ref ->
                Toast.makeText(
                    applicationContext, "Success!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    applicationContext, "Error: $e",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}