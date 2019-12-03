package cmsc436.mobilesurvey.forms

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

import com.google.firebase.auth.FirebaseAuth


import cmsc436.mobilesurvey.R
import kotlin.math.log

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window.FEATURE_NO_TITLE
import android.widget.*
import cmsc436.mobilesurvey.models.User
import cmsc436.mobilesurvey.utils.Database

import cmsc436.mobilesurvey.utils.QRCodeUtils

class CreateSurveyActivity : AppCompatActivity() {

    enum class FormType {
        RETAIL
    }

    private var mAuth: FirebaseAuth? = null
    private var createButton: Button? = null
    private var mUserView: TextView? = null
    private var qrCodeUtils: QRCodeUtils? = QRCodeUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("TAG", "INFO: INSIDE ON CREATE")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createsurvey)
        mAuth = FirebaseAuth.getInstance()


//        val spinner = findViewById<Spinner>(R.id.spinner)
//        val adapter = ArrayAdapter.createFromResource(
//            this,
//            R.array.survey_type, android.R.layout.simple_spinner_dropdown_item
//        )
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = adapter

        mUserView = findViewById(R.id.userView)
        createButton = findViewById(R.id.create)
        qrCodeUtils = QRCodeUtils()

        var username = qrCodeUtils!!.getUser()[0].toUpperCase().toString()
        username = username + qrCodeUtils!!.getUser().substring(1)

        mUserView!!.text = mUserView!!.text.toString() + username

        //button will generate code and store it in the database
        //TODO: Pull from DB
        createButton!!.setOnClickListener {
            Database.db.collection("users").document(mAuth!!.currentUser!!.uid)
                .get()
                .addOnSuccessListener { document ->
                    lateinit var user: User
                    user = User(document)
                    user.type.toString()
                    val bitmap = qrCodeUtils!!.TextToImageEncode(qrCodeUtils!!.getUser() + "&&&" + user.type.toString() + "&&&" + user.id.toString())
                    val bytes = qrCodeUtils!!.convertBitmapToByteArray(bitmap!!)
                    val code = qrCodeUtils!!.convertCompressedByteArrayToBitmap(bytes)
                    createQR(code)
                }

        }

    }

    fun createQR(qr: Bitmap) {
        val builder = Dialog(this)
        builder.requestWindowFeature(FEATURE_NO_TITLE)
        builder.window!!.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
        builder.setOnDismissListener {
            //nothing;
        }

        val imageView = ImageView(this)
        imageView.setImageBitmap(qr)
        builder.addContentView(
            imageView, RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        builder.show()
    }


}