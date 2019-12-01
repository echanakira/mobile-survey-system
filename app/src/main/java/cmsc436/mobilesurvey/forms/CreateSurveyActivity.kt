package cmsc436.mobilesurvey.forms

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

import cmsc436.mobilesurvey.R
import cmsc436.mobilesurvey.utils.QRCodeUtils
import android.app.Dialog
import android.view.Window

import android.view.ViewGroup
import android.widget.RelativeLayout
import android.graphics.drawable.ColorDrawable





class CreateSurveyActivity : AppCompatActivity() {
    private var createButton: Button? = null
    private var scanBtn: Button? = null

    private var name: EditText? = null
    private var qrCodeUtils: QRCodeUtils? = null
    private var db: SurveyDatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("TAG", "INFO: INSIDE ON CREATE")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createsurvey)

        createButton = findViewById(R.id.create)
        qrCodeUtils = QRCodeUtils()
        db = SurveyDatabaseHandler()
        db!!.setupDB()

        //button will generate code and store it in the database
        //TODO: Pull from DB
        createButton!!.setOnClickListener {
            val qrcode = qrCodeUtils!!.generateQRCode(name!!.text.toString())
            val bytes = qrCodeUtils!!.convertBitmapToByteArray(qrcode!!)

            db!!.addFormToDatabase(name!!.text.toString(), "", bytes)
            db!!.getFormFromDatabase()

            createQR(qrCodeUtils!!.convertCompressedByteArrayToBitmap(bytes))
        }
    }

    fun createQR(qr: Bitmap){
        val builder = Dialog(this)
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
        builder.window!!.setBackgroundDrawable(
            ColorDrawable(android.graphics.Color.TRANSPARENT)
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