package cmsc436.mobilesurvey.utils

import android.os.Bundle
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import cmsc436.mobilesurvey.R
import com.google.zxing.integration.android.IntentIntegrator
import android.widget.Toast
import android.content.Intent
import android.util.Log
import cmsc436.mobilesurvey.forms.SurveyActivity

class ScanActivity : AppCompatActivity(){


    private var scanBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        scanBtn = findViewById(R.id.scan)
        scanBtn!!.setOnClickListener{
            val scanner = IntentIntegrator(this)
            Log.i("TAG", "INFO: Initiating Scan")
            scanner.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                val user = result.contents.split("&&&")[0]
                Toast.makeText(this, "Scanned " + user, Toast.LENGTH_LONG).show()
                val type = result.contents.split("&&&")[1]
                //TODO: Return an intent with type and user
                val intent = Intent(this@ScanActivity, SurveyActivity::class.java)
                intent.putExtra("type", type)
                intent.putExtra("user", user)
                startActivity(intent)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}