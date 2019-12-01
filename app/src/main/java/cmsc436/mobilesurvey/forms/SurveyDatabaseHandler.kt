package cmsc436.mobilesurvey.forms

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import cmsc436.mobilesurvey.data.FormData
import android.util.Base64
import cmsc436.mobilesurvey.data.DecodedFormData
import cmsc436.mobilesurvey.utils.QRCodeUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore


class SurveyDatabaseHandler {
//
//    lateinit var encodedForms: MutableList<FormData>
//    lateinit var decodedForms: MutableList<DecodedFormData>
//
//    val db = FirebaseDatabase.getInstance().getReference("forms")
//    val firestore = FirebaseFirestore.getInstance()
//
//    private var QRCodeUtils: QRCodeUtils? = null
//
//    fun addFormToDatabase(name: String, content: String, qrcode: ByteArray) {
//        val code = Base64.encodeToString(qrcode, Base64.DEFAULT)
//        val form = FormData(name, content, code)
//        db.child(name).child(form.id).setValue(form)
//    }
//
//    fun getFormFromDatabase(): Bitmap {
//        Log.i("TAG", "INFO: " + decodedForms[0] + " was added")
//        return QRCodeUtils!!.convertCompressedByteArrayToBitmap(decodedForms[0].qrcode)
//    }
//
//    fun setupDB() {
//        encodedForms = mutableListOf()
//        decodedForms = mutableListOf()
//        db.orderByChild("name").equalTo("Name 2")
//        db.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                if (p0!!.exists()) {
//
//                    for (form in p0.children) {
//                        val f = form.getValue(FormData::class.java)
//                        encodedForms.add(f!!)
//
//
//                        val decoded = DecodedFormData(
//                            f.name,
//                            f.content,
//                            Base64.decode(f.qrcode, Base64.DEFAULT)
//                        )
//                        decodedForms.add(decoded)
//                    }
//                }
//            }
//
//        })
//
//    }

}