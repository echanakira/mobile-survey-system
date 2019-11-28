package cmsc436.mobilesurvey.forms
import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import cmsc436.mobilesurvey.data.FormData
import android.util.Base64


class SurveyDatabaseHandler{


//    val database = FirebaseDatabase.getInstance().reference
    val db = FirebaseDatabase.getInstance().getReference("forms")


    fun addFormToDatabase(name: String, content: String, qrcode: ByteArray){
        val code = Base64.encodeToString(qrcode, Base64.DEFAULT)
        val form = FormData(name, content, code)
        db.child(name).child(form.id).setValue(form)
        Log.i("TAG", "Form " + form.id + " added")
    }

    fun getForm(name: String){
//        database.child("forms").
    }

}